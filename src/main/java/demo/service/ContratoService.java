package demo.service;

import demo.domain.Contrato;
import demo.dto.ContratoDto;
import demo.repository.ContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class ContratoService {

    private final ContratoRepository repository;

    private final SqsAsyncClient sqsAsyncClient;

    private final SqsClient sqsClient;

    public void sendMessage(String url, String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(url)
                .messageBody(message)
                .build();
        sqsAsyncClient.sendMessage(sendMessageRequest);
    }

    public void CriarFila(String queueName) {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .build();
        sqsAsyncClient.createQueue(createQueueRequest);
    }

    public AtomicReference<String> consumerMessage() {
        AtomicReference<String> body = new AtomicReference<>();

        GetQueueUrlResponse getLocalstackContrato =
                sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName("minha-fila").build());

        String filaUrl = getLocalstackContrato.queueUrl();
        while (true) {
            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                    .queueUrl(filaUrl)
                    .maxNumberOfMessages(1)
                    .waitTimeSeconds(5)
                    .build());
            List<Message> messages = receiveMessageResponse.messages();
            messages.stream().forEach(message -> {
                body.set(message.body());
            });
            return body;
        }
    }

    public void salvar(ContratoDto dto) {
        repository.save(new Contrato(dto));
    }

    public List<Contrato> listar() {
        return repository.findAll();
    }
}

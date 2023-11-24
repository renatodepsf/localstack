package demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.Contrato;
import demo.dto.ContratoDto;
import demo.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<Void> postarNaFila(@RequestBody ContratoDto dto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String message = mapper.writeValueAsString(dto);
            contratoService.sendMessage("/000000000000/minha-fila", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<AtomicReference<String>> retornaMensagemFila() {
        AtomicReference<String> stringAtomicReference = contratoService.consumerMessage();
        return new ResponseEntity<>(stringAtomicReference, HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public void salvarContrato(@RequestBody ContratoDto dto) {
        contratoService.salvar(dto);
    }
    @GetMapping("/listar")
    public List<Contrato> retornaContrato() {
        return contratoService.listar();
    }
}

package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(awsCredentialsProvider())
                .endpointOverride(URI.create("http://sqs.sa-east-1.localhost.localstack.cloud:4566"))
                .build();
    }

    @Bean
    public SqsClient sqsClient() {

        return SqsClient.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(awsCredentialsProvider())
                .endpointOverride(URI.create("http://sqs.sa-east-1.localhost.localstack.cloud:4566"))
                .build();
    }

    public AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider
                .create(AwsBasicCredentials.create("foobar", "foobar"));
    }
}

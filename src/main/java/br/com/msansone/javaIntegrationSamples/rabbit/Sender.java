package br.com.msansone.javaIntegrationSamples.rabbit;

import br.com.msansone.javaIntegrationSamples.JavaIntegrationSamplesApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Sender {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Sender(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    public void send(String message) throws InterruptedException {
        rabbitTemplate.convertAndSend(
                JavaIntegrationSamplesApplication.topicExchangeName,
                "foo.bar.baz",
                message);
    }
}

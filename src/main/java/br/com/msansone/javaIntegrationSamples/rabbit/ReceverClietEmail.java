package br.com.msansone.javaIntegrationSamples.rabbit;

import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ReceverClietEmail {

    private CountDownLatch latch = new CountDownLatch(1);


    @RabbitListener(queues = "client-email")
    public void consumerDefaultMessage(final Message message){
        System.out.println("Received from client-email queue <" + message + ">");

        try{
            ObjectMapper obj = new ObjectMapper();
            Client client = obj.readValue(message.getPayload().toString(), Client.class);
            System.out.println("Nome do cliente: " + client.getName());

        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("erro convertendo mensagem " +message);
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }


}

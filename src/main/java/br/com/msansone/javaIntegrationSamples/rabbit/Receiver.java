package br.com.msansone.javaIntegrationSamples.rabbit;

import java.util.concurrent.CountDownLatch;

import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        try{
            ObjectMapper obj = new ObjectMapper();
            Client client = obj.readValue(message, Client.class);
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
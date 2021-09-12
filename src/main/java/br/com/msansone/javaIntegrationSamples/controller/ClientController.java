package br.com.msansone.javaIntegrationSamples.controller;

import br.com.msansone.javaIntegrationSamples.rabbit.Sender;
import br.com.msansone.javaIntegrationSamples.service.ClientService;
import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private Sender sender;

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/carga")
    public ResponseEntity<String> carregaDados(){
        clientService.save(new Client("Marcio Sansone","msansone@gmail.com", null));
        clientService.save(new Client("Maria Sansone","maria@gmail.com", null));
        clientService.save(new Client("Jose Sansone","jose@gmail.com", null));
        clientService.save(new Client("Antonia Sansone","antonia@gmail.com", null));
        clientService.save(new Client("Pedro Sansone","pedro@gmail.com", null));
        return ResponseEntity.ok("dados carregados");
    }

    @GetMapping("/rabbitMQ")
    public ResponseEntity<String> rabbitMQ() throws InterruptedException {
        sender.send("Ola mundo");
        return ResponseEntity.ok("mensagem enviada com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) throws InterruptedException {
        Client client = clientService.getById(id);
        if (client==null) {
            return ResponseEntity.notFound().build();
        }
        sender.send(convertToJson(client));
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client clientSaved = clientService.save(client);
        return ResponseEntity.ok(clientSaved);
    }

    private String convertToJson(Client cliente){
        ObjectMapper obj = new ObjectMapper();
        try {
            // Converting the Java object into a JSON string
            String jsonStr = obj.writeValueAsString(cliente);
            // Displaying Java object into a JSON string
            return jsonStr;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}

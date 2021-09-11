package br.com.msansone.javaIntegrationSamples.controller;

import br.com.msansone.javaIntegrationSamples.service.ClientService;
import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){
        Client client = clientService.getById(id);
        if (client==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client clientSaved = clientService.save(client);
        return ResponseEntity.ok(clientSaved);
    }


}

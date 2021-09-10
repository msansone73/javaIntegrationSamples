package br.com.msansone.javaIntegrationSamples.controller;

import br.com.msansone.javaIntegrationSamples.service.ClientService;
import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

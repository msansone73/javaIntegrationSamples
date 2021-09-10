package br.com.msansone.javaIntegrationSamples.service;

import br.com.msansone.javaIntegrationSamples.repository.ClientRepository;
import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}

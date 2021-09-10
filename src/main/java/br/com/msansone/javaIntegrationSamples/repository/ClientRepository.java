package br.com.msansone.javaIntegrationSamples.repository;

import br.com.msansone.javaIntegrationSamplesApplication.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

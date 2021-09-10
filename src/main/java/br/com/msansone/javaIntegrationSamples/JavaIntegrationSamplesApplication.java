package br.com.msansone.javaIntegrationSamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.jdbc.Sql;

@EntityScan(basePackages = {"br.com.msansone.javaIntegrationSamplesApplication.model"})
@SpringBootApplication
public class JavaIntegrationSamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaIntegrationSamplesApplication.class, args);
	}

}

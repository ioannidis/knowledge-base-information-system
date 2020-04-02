package eu.ioannidis.vks.infrastructureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableJpaAuditing
@EnableCircuitBreaker
@EnableResourceServer
public class InfrastructureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfrastructureServiceApplication.class, args);
	}

}

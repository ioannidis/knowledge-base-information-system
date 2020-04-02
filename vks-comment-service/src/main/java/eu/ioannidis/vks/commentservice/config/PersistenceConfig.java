package eu.ioannidis.vks.commentservice.config;

import eu.ioannidis.vks.commentservice.utils.AuditorAwareSetup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.UUID;

@Configuration
public class PersistenceConfig {

    @Bean
    AuditorAware<UUID> auditorProvider() {
        return new AuditorAwareSetup();
    }

}

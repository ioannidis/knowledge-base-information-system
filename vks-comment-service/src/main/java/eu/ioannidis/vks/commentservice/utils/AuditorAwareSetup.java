package eu.ioannidis.vks.commentservice.utils;

import eu.ioannidis.vks.commentservice.models.PrincipalModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;


public class AuditorAwareSetup implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        LinkedHashMap<String, Object> principal =  (LinkedHashMap<String, Object>) authentication.getPrincipal();

        return Optional.of(UUID.fromString(principal.get("id").toString()));
    }
}

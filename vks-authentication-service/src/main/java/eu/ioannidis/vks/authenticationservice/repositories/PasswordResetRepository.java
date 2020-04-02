package eu.ioannidis.vks.authenticationservice.repositories;

import eu.ioannidis.vks.authenticationservice.models.entities.PasswordResetEntity;
import eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys.PasswordResetKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetRepository extends JpaRepository<PasswordResetEntity, PasswordResetKey> {
    Optional<PasswordResetEntity> findByPasswordResetKeyUserId(UUID userId);

    Optional<PasswordResetEntity> findByPasswordResetKeyToken(String token);
}

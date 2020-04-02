package eu.ioannidis.vks.authenticationservice.services;

import eu.ioannidis.vks.authenticationservice.models.entities.PasswordResetEntity;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetService {
    Optional<PasswordResetEntity> findByUserId(String userId);

    Optional<PasswordResetEntity> findByToken(String token);

    <S extends PasswordResetEntity> S save(S entity);
}

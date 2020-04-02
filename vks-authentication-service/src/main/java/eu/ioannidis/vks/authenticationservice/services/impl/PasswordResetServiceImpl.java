package eu.ioannidis.vks.authenticationservice.services.impl;

import eu.ioannidis.vks.authenticationservice.models.entities.PasswordResetEntity;
import eu.ioannidis.vks.authenticationservice.repositories.PasswordResetRepository;
import eu.ioannidis.vks.authenticationservice.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PasswordResetServiceImpl implements PasswordResetService {

    private PasswordResetRepository passwordResetRepository;

    @Autowired
    public PasswordResetServiceImpl(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    @Override
    public Optional<PasswordResetEntity> findByUserId(String userId) {
        return passwordResetRepository.findByPasswordResetKeyUserId(UUID.fromString(userId));
    }

    @Override
    public <S extends PasswordResetEntity> S save(S entity) {
        return passwordResetRepository.save(entity);
    }

    @Override
    public Optional<PasswordResetEntity> findByToken(String token) {
        return passwordResetRepository.findByPasswordResetKeyToken(token);
    }
}

package eu.ioannidis.vks.infrastructureservice.services;

import eu.ioannidis.vks.infrastructureservice.models.entities.SystemEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SystemService {
    List<SystemEntity> findAll();

    Optional<SystemEntity> findById(UUID uuid);

    <S extends SystemEntity> S save(S entity);

    void deleteById(UUID uuid);

    boolean existsById(UUID uuid);

    long count();
}

package eu.ioannidis.vks.infrastructureservice.services;

import eu.ioannidis.vks.infrastructureservice.models.entities.OemEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OemService {
    List<OemEntity> findAll();

    Optional<OemEntity> findById(UUID uuid);

    <S extends OemEntity> S save(S entity);

    void deleteById(UUID uuid);

    boolean existsById(UUID uuid);

    long count();
}

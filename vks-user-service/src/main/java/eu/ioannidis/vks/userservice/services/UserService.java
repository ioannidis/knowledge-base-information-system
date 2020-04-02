package eu.ioannidis.vks.userservice.services;

import eu.ioannidis.vks.userservice.models.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(UUID uuid);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    <S extends UserEntity> S save(S entity);

    void deleteById(UUID uuid);

    boolean existsById(UUID uuid);

    long count();
}

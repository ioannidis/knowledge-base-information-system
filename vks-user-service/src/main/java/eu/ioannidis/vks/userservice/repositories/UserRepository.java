package eu.ioannidis.vks.userservice.repositories;

import eu.ioannidis.vks.userservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Override
    List<UserEntity> findAll();

    @Override
    Optional<UserEntity> findById(UUID uuid);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    boolean existsById(UUID uuid);

    @Override
    long count();
}

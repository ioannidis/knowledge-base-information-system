package eu.ioannidis.vks.authenticationservice.repositories;

import eu.ioannidis.vks.authenticationservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, UUID> {

    @Override
    Optional<UserEntity> findById(UUID uuid);

    Optional<UserEntity> findByEmail(String email);

    @Override
    <S extends UserEntity> S save(S entity);


}

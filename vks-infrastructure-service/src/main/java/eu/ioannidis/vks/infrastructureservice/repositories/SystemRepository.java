package eu.ioannidis.vks.infrastructureservice.repositories;

import eu.ioannidis.vks.infrastructureservice.models.entities.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SystemRepository extends JpaRepository<SystemEntity, UUID> {
}

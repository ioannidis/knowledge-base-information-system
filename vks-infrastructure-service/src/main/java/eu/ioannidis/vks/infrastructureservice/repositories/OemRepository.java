package eu.ioannidis.vks.infrastructureservice.repositories;

import eu.ioannidis.vks.infrastructureservice.models.entities.OemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OemRepository extends JpaRepository<OemEntity, UUID> {
}

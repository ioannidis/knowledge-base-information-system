package eu.ioannidis.vks.infrastructureservice.repositories;

import eu.ioannidis.vks.infrastructureservice.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}

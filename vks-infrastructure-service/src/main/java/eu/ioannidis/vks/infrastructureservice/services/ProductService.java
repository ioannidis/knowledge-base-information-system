package eu.ioannidis.vks.infrastructureservice.services;

import eu.ioannidis.vks.infrastructureservice.models.entities.ProductEntity;
import eu.ioannidis.vks.infrastructureservice.models.responses.ProductIdTitleResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<ProductEntity> findAll();

//    <T> List<T> findAllProjection(Class<T> type);

    Optional<ProductEntity> findById(UUID uuid);

    <S extends ProductEntity> S save(S entity);

    void deleteById(UUID uuid);

    boolean existsById(UUID uuid);

    long count();
}

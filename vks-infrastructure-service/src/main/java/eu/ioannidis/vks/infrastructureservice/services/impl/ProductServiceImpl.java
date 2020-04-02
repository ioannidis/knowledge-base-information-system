package eu.ioannidis.vks.infrastructureservice.services.impl;

import eu.ioannidis.vks.infrastructureservice.models.entities.ProductEntity;
//import eu.ioannidis.vks.infrastructureservice.models.responses.ProductIdTitleResponse;
//import eu.ioannidis.vks.infrastructureservice.repositories.ProductProjectionRepository;
import eu.ioannidis.vks.infrastructureservice.repositories.ProductRepository;
import eu.ioannidis.vks.infrastructureservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

//    private ProductProjectionRepository productProjectionRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

//    @Override
//    public <T> List<T> findAllProjection(Class<T> type) {
//        return productProjectionRepository.findAllBy(type);
//    }

    @Override
    public Optional<ProductEntity> findById(UUID uuid) {
        return productRepository.findById(uuid);
    }

    @Override
    public <S extends ProductEntity> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        productRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return productRepository.existsById(uuid);
    }

    @Override
    public long count() {
        return productRepository.count();
    }
}

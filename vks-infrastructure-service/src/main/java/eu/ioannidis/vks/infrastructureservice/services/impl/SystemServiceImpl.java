package eu.ioannidis.vks.infrastructureservice.services.impl;

import eu.ioannidis.vks.infrastructureservice.models.entities.SystemEntity;
import eu.ioannidis.vks.infrastructureservice.repositories.SystemRepository;
import eu.ioannidis.vks.infrastructureservice.services.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SystemServiceImpl implements SystemService {

    private SystemRepository systemRepository;

    @Autowired
    public SystemServiceImpl(SystemRepository systemRepository) {
        this.systemRepository = systemRepository;
    }

    @Override
    public List<SystemEntity> findAll() {
        return systemRepository.findAll();
    }

    @Override
    public Optional<SystemEntity> findById(UUID uuid) {
        return systemRepository.findById(uuid);
    }

    @Override
    public <S extends SystemEntity> S save(S entity) {
        return systemRepository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        systemRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return systemRepository.existsById(uuid);
    }

    @Override
    public long count() {
        return systemRepository.count();
    }
}

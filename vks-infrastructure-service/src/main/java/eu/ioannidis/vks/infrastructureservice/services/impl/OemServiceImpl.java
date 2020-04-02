package eu.ioannidis.vks.infrastructureservice.services.impl;

import eu.ioannidis.vks.infrastructureservice.models.entities.OemEntity;
import eu.ioannidis.vks.infrastructureservice.repositories.OemRepository;
import eu.ioannidis.vks.infrastructureservice.services.OemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OemServiceImpl implements OemService {

    private OemRepository oemRepository;

    @Autowired
    public OemServiceImpl(OemRepository oemRepository) {
        this.oemRepository = oemRepository;
    }

    @Override
    public List<OemEntity> findAll() {
        return oemRepository.findAll();
    }

    @Override
    public Optional<OemEntity> findById(UUID uuid) {
        return oemRepository.findById(uuid);
    }

    @Override
    public <S extends OemEntity> S save(S entity) {
        return oemRepository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        oemRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return oemRepository.existsById(uuid);
    }

    @Override
    public long count() {
        return oemRepository.count();
    }
}

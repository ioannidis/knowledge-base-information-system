package eu.ioannidis.vks.userservice.services.impl;

import eu.ioannidis.vks.userservice.models.entities.UserEntity;
import eu.ioannidis.vks.userservice.repositories.UserRepository;
import eu.ioannidis.vks.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return userRepository.existsById(uuid);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}

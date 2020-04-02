package eu.ioannidis.vks.commentservice.services.impl;

import eu.ioannidis.vks.commentservice.models.entities.CommentEntity;
import eu.ioannidis.vks.commentservice.repositories.CommentRepository;
import eu.ioannidis.vks.commentservice.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<CommentEntity> findAllByVulnerability(UUID vulnerabilityId) {
        return commentRepository.findAllByVulnerability(vulnerabilityId);
    }

    @Override
    public Optional<CommentEntity> findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public <S extends CommentEntity> S save(S entity) {
        return this.commentRepository.save(entity);
    }
}

package eu.ioannidis.vks.commentservice.services;

import eu.ioannidis.vks.commentservice.models.entities.CommentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {

    List<CommentEntity> findAllByVulnerability(UUID vulnerabilityId);

    Optional<CommentEntity> findById(int id);

    <S extends CommentEntity> S save(S entity);

}

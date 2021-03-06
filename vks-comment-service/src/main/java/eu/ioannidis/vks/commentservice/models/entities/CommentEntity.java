package eu.ioannidis.vks.commentservice.models.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotNull
//    @NotEmpty
//    @Column(name = "user_id")
//    private UUID user;

    @Column(name = "vulnerability_id")
    private UUID vulnerability;

    @NotNull
    @NotEmpty
    @Column(name = "content")
    private String content;

    public CommentEntity() {
    }

    public CommentEntity(int id, UUID vulnerabilityId, String content, UUID createdBy, UUID modifiedBy, Date createdAt, Date modifiedAt) {
        super(createdBy, modifiedBy, createdAt, modifiedAt);
        this.id = id;
//        this.user = userId;
        this.vulnerability = vulnerabilityId;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public UUID getUser() {
//        return user;
//    }
//
//    public void setUser(UUID userId) {
//        this.user = userId;
//    }

    public UUID getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(UUID vulnerabilityId) {
        this.vulnerability = vulnerabilityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

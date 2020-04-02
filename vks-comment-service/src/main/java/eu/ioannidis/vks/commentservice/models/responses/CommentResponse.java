package eu.ioannidis.vks.commentservice.models.responses;

import eu.ioannidis.vks.commentservice.models.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class CommentResponse {

    private int id;

    private UUID vulnerability;

    private String content;

    private UserEntity createdBy;

    private UserEntity modifiedBy;

    private Date createdAt;

    private Date modifiedAt;

    public CommentResponse() {
    }

    public CommentResponse(int id, UUID vulnerability, String content, UserEntity createdBy, UserEntity modifiedBy, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.vulnerability = vulnerability;
        this.content = content;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getVulnerability() {
        return vulnerability;
    }

    public void setVulnerability(UUID vulnerability) {
        this.vulnerability = vulnerability;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "id=" + id +
                ", vulnerability=" + vulnerability +
                ", content='" + content + '\'' +
                ", createdBy=" + createdBy +
                ", modifiedBy=" + modifiedBy +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}

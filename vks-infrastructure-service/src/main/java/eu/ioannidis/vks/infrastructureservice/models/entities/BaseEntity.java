package eu.ioannidis.vks.infrastructureservice.models.entities;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

//    @Column(name = "created_by")
//    @CreatedBy
//    private UUID createdBy;
//
//    @Column(name = "created_by")
//    @LastModifiedBy
//    private UUID modifiedBy;

    @Column(name = "created_at",  nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "modified_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedAt;

    public BaseEntity() {
    }

    public BaseEntity(Date createdAt, Date modifiedAt) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
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
}

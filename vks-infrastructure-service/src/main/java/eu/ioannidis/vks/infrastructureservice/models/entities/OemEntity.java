package eu.ioannidis.vks.infrastructureservice.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "oems")
@EntityListeners(AuditingEntityListener.class)
public class OemEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotNull
    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @NotNull
    @NotEmpty
    @Column(name = "slug", nullable = false)
    private String slug;

    public OemEntity() {
    }

    public OemEntity(Date createdAt, Date modifiedAt, UUID id, @NotNull @NotEmpty String title, String description, @NotNull @NotEmpty String slug) {
        super(createdAt, modifiedAt);
        this.id = id;
        this.title = title;
        this.description = description;
        this.slug = slug;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "OemEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }
}

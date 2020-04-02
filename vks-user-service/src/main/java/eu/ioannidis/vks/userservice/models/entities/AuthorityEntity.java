package eu.ioannidis.vks.userservice.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eu.ioannidis.vks.userservice.models.entities.embeddablekeys.AuthorityKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

    @EmbeddedId
    private AuthorityKey authorityKey;

    @JsonIgnoreProperties("authorities")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;

    public AuthorityEntity() {
    }

    public AuthorityEntity(AuthorityKey authorityKey) {
        this.authorityKey = authorityKey;
    }

    public AuthorityEntity(AuthorityKey authorityKey, UserEntity user) {
        this.authorityKey = authorityKey;
        this.user = user;
    }

    public AuthorityKey getAuthorityKey() {
        return authorityKey;
    }

    public void setAuthorityKey(AuthorityKey authorityKey) {
        this.authorityKey = authorityKey;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityEntity)) return false;
        AuthorityEntity company = (AuthorityEntity) o;
        return Objects.equals(getAuthorityKey(), company.getAuthorityKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthorityKey());
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "authorityKey=" + authorityKey +
                '}';
    }
}

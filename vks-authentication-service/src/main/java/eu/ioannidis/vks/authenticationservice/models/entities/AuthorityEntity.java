package eu.ioannidis.vks.authenticationservice.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys.AuthorityKey;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

    @EmbeddedId
    private AuthorityKey authorityKey = new AuthorityKey();

    @JsonIgnoreProperties("authorities")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    public String toString() {
        return "AuthorityEntity{" +
                "authorityKey=" + authorityKey +
                '}';
    }
}

package eu.ioannidis.vks.userservice.models.entities.embeddablekeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AuthorityKey implements Serializable {

    @JoinColumn(name = "user_id")
    protected UUID userId;

    @Column(name = "authority")
    protected String authority;

    public AuthorityKey() {
    }

    public AuthorityKey(String authority) {
        this.authority = authority;
    }

    public AuthorityKey(UUID userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityKey)) return false;
        AuthorityKey that = (AuthorityKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getAuthority(), that.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getAuthority());
    }

    @Override
    public String toString() {
        return "AuthorityKey{" +
                "userId=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
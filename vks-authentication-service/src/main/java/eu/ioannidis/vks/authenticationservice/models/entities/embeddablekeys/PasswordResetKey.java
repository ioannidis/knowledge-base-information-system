package eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class PasswordResetKey implements Serializable {

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "token", updatable = false, nullable = false)
    private String token ;

    public PasswordResetKey() {
    }

    public PasswordResetKey(UUID userId) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

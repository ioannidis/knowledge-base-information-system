package eu.ioannidis.vks.authenticationservice.models.entities;

import eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys.PasswordResetKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset")
public class PasswordResetEntity {

    @EmbeddedId
    private PasswordResetKey passwordResetKey;

    @Column(name = "active")
    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_at")
    private Date expireAt;

    public PasswordResetEntity() {
    }

    public PasswordResetEntity(PasswordResetKey passwordResetKey, boolean active, Date expireAt) {
        this.passwordResetKey = passwordResetKey;
        this.active = active;
        this.expireAt = expireAt;
    }

    public PasswordResetKey getPasswordResetKey() {
        return passwordResetKey;
    }

    public void setPasswordResetKey(PasswordResetKey passwordResetKey) {
        this.passwordResetKey = passwordResetKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}

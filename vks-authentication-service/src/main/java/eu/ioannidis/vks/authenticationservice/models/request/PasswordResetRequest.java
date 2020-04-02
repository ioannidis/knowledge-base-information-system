package eu.ioannidis.vks.authenticationservice.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PasswordResetRequest {

    @Email
    @NotNull
    @NotEmpty
    private String email;

    public PasswordResetRequest() {
    }

    public PasswordResetRequest(@Email @NotNull @NotEmpty String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PasswordResetRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}

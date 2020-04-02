package eu.ioannidis.vks.authenticationservice.models.request;

import eu.ioannidis.vks.authenticationservice.utils.validators.fieldmatch.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@FieldMatch(firstField = "password", secondField = "confirmPassword", message = "The password fields must match.")
public class PasswordUpdateRequest {

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

    public PasswordUpdateRequest() {
    }

    public PasswordUpdateRequest(@NotNull @NotEmpty String password, @NotNull @NotEmpty String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PasswordUpdateRequest{" +
                "password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

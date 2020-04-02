package eu.ioannidis.vks.userservice.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateUserRequest {

    @NotNull
    @NotEmpty
    private String id;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private boolean enabled;

    private String[] authorities;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(@NotNull @NotEmpty String id, @Email @NotNull @NotEmpty String email, @NotNull @NotEmpty String username, @NotNull @NotEmpty String password, boolean enabled, String[] authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public UpdateUserRequest(@NotNull @NotEmpty String id, @Email @NotNull @NotEmpty String email, @NotNull @NotEmpty String username, boolean enabled, String[] authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }
}

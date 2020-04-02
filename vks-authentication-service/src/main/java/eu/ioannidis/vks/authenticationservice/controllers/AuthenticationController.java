package eu.ioannidis.vks.authenticationservice.controllers;

import eu.ioannidis.vks.authenticationservice.models.EmailModel;
import eu.ioannidis.vks.authenticationservice.models.entities.PasswordResetEntity;
import eu.ioannidis.vks.authenticationservice.models.entities.UserEntity;
import eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys.PasswordResetKey;
import eu.ioannidis.vks.authenticationservice.models.request.PasswordResetRequest;
import eu.ioannidis.vks.authenticationservice.models.request.PasswordUpdateRequest;
import eu.ioannidis.vks.authenticationservice.models.request.SignUpRequest;
import eu.ioannidis.vks.authenticationservice.services.AuthUserDetailsService;
import eu.ioannidis.vks.authenticationservice.services.PasswordResetService;
import eu.ioannidis.vks.authenticationservice.utils.feignclients.EmailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(AuthenticationController.BASE_URL)
public class AuthenticationController {
    static final String BASE_URL = "/oauth";

    private AuthUserDetailsService authUserDetailsService;

    private PasswordResetService passwordResetService;

    private EmailFeignClient emailFeignClient;

    public AuthenticationController(AuthUserDetailsService authUserDetailsService, PasswordResetService passwordResetService, EmailFeignClient emailFeignClient) {
        this.authUserDetailsService = authUserDetailsService;
        this.passwordResetService = passwordResetService;
        this.emailFeignClient = emailFeignClient;
    }

    @GetMapping("/user")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put( "authorities", AuthorityUtils.authorityListToSet(
                        user.getUserAuthentication()
                                .getAuthorities()
                )
        );
        return userInfo;
    }

    @PostMapping("/signup")
    public ResponseEntity userSignUp(@Valid @RequestBody SignUpRequest signUpRequest, Error error) {
        UserEntity user = authUserDetailsService.createUser(signUpRequest);

        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Administrators have been informed about your registration. Your account will be" +
                    " activated as soon as possible.");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/passwordreset")
    public ResponseEntity resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {

        // Find user by email
        Optional<UserEntity> user = authUserDetailsService.findByEmail(passwordResetRequest.getEmail());

        if (user.isPresent()) {
            // Create password reset entity that contains the corresponding data for password change
            PasswordResetEntity passwordResetEntity = new PasswordResetEntity(new PasswordResetKey(user.get().getId()), true, new Date((new Date()).getTime() + (1000 * 60 * 60 * 24)));
            passwordResetService.save(passwordResetEntity);

            // Create and send email
            EmailModel emailModel = new EmailModel(
                    "panos277@hotmail.com",
                    passwordResetRequest.getEmail(),
                    "[VKS] Password reset",
                    "Click on the following link to reset your password. " +
                            "http://localhost:8082/oauth/passwordreset/" + passwordResetEntity.getPasswordResetKey().getToken()
            );
            emailFeignClient.sendEmail(emailModel);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Password reset email has been sent.");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/passwordreset/{token}")
    public ResponseEntity updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest, @PathVariable String token) {

        Optional<PasswordResetEntity> optionalPasswordResetEntity = passwordResetService.findByToken(token);
        PasswordResetEntity passwordResetEntity;
        if (optionalPasswordResetEntity.isPresent()) {
            passwordResetEntity = optionalPasswordResetEntity.get();

            if (!passwordResetEntity.isActive() || !Instant.now().isBefore(passwordResetEntity.getExpireAt().toInstant())) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = authUserDetailsService.findById(passwordResetEntity.getPasswordResetKey().getUserId()).get();

        UserEntity updatedUser = authUserDetailsService.update(userEntity, passwordUpdateRequest.getPassword());

        if (updatedUser != null) {
            passwordResetEntity.setActive(false);
            passwordResetService.save(passwordResetEntity);
        }

        return new ResponseEntity(userEntity, HttpStatus.OK);
    }

}

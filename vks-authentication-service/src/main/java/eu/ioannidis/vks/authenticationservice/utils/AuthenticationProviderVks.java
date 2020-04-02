package eu.ioannidis.vks.authenticationservice.utils;

import eu.ioannidis.vks.authenticationservice.models.UserModel;
import eu.ioannidis.vks.authenticationservice.services.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderVks implements AuthenticationProvider {

    private AuthUserDetailsService authUserDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationProviderVks(AuthUserDetailsService authUserDetailsService, PasswordEncoder passwordEncoder) {
        this.authUserDetailsService = authUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserModel principal = (UserModel) authUserDetailsService.loadUserByUsername(authentication.getName());

        if (!principal.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), principal.getPassword())) {
            throw new OAuth2Exception("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(principal,null, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

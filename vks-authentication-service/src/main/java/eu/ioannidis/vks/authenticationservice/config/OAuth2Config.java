package eu.ioannidis.vks.authenticationservice.config;

import com.netflix.discovery.converters.Auto;
import eu.ioannidis.vks.authenticationservice.services.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;

    private AuthUserDetailsService authUserDetailsService;

    private WebResponseExceptionTranslator oAuth2ExceptionTranslatorConfig;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public OAuth2Config(AuthenticationManager authenticationManager, AuthUserDetailsService authUserDetailsService, WebResponseExceptionTranslator oAuth2ExceptionTranslatorConfig, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUserDetailsService = authUserDetailsService;
        this.oAuth2ExceptionTranslatorConfig = oAuth2ExceptionTranslatorConfig;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("vksClient")
                .secret(passwordEncoder.encode("KM6S@cn"))
                .authorizedGrantTypes(
                        "refresh_token",
                        "password",
                        "client_credentials")
                .scopes("webclient","mobileclient");
//                .accessTokenValiditySeconds(1800);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(authUserDetailsService)
                .exceptionTranslator(oAuth2ExceptionTranslatorConfig);
    }
}

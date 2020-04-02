package eu.ioannidis.vks.authenticationservice.config;

import eu.ioannidis.vks.authenticationservice.services.AuthUserDetailsService;
import eu.ioannidis.vks.authenticationservice.utils.AuthenticationProviderVks;
import eu.ioannidis.vks.authenticationservice.utils.VksAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    private AuthenticationProviderVks authenticationProvider;

    private AuthUserDetailsService authUserDetailsService;

    private VksAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public WebSecurityConfig(DataSource dataSource,
                             VksAuthenticationEntryPoint authenticationEntryPoint) {
        this.dataSource = dataSource;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    private void setAuthUserDetailsService(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Autowired
    private void setAuthenticationProvider(AuthenticationProviderVks authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .parentAuthenticationManager(authenticationManagerBean())
                .authenticationProvider(authenticationProvider);

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

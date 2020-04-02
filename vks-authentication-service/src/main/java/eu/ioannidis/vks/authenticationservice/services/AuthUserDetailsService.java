package eu.ioannidis.vks.authenticationservice.services;

import eu.ioannidis.vks.authenticationservice.models.UserModel;
import eu.ioannidis.vks.authenticationservice.models.entities.AuthorityEntity;
import eu.ioannidis.vks.authenticationservice.models.entities.UserEntity;
import eu.ioannidis.vks.authenticationservice.models.entities.embeddablekeys.AuthorityKey;
import eu.ioannidis.vks.authenticationservice.models.request.SignUpRequest;
import eu.ioannidis.vks.authenticationservice.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthUserDetailsService implements UserDetailsService {

    private AuthRepository authRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> user = authRepository.findByEmail(s);

        if (!user.isPresent()) {
            throw new OAuth2Exception("Bad credentials.");
        }

        UserEntity userData = user.get();
        return new UserModel(userData.getId(), userData.getUsername(), userData.getPassword(), userData.getEmail(), userData.isEnabled(), getAuthorities(userData.getAuthorities()));
    }

    // Returns a collection with the granted authorities
    private Collection<GrantedAuthority> getAuthorities(Collection<AuthorityEntity> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityKey().getAuthority())).collect(Collectors.toList());
    }

    public UserEntity createUser(SignUpRequest signUpRequest) {
        UserEntity user = new UserEntity();

        String username = signUpRequest.getEmail().split("@")[0];

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setEnabled(false);

        AuthorityEntity authority = new AuthorityEntity( new AuthorityKey("USER"));

        Set<AuthorityEntity> authorities = new HashSet<>();
        authorities.add(authority);

        user.setAuthorities(authorities);
        authority.setUser(user);

        authRepository.save(user);

        return user;
    }

    public Optional<UserEntity> findByEmail(String email) {
        return authRepository.findByEmail(email);
    }

    public Optional<UserEntity> findById(String id) {
        return authRepository.findById(UUID.fromString(id));
    }

    public Optional<UserEntity> findById(UUID id) {
        return authRepository.findById(id);
    }

    public UserEntity update(UserEntity userEntity, String password) {
        userEntity.setPassword(passwordEncoder.encode(password));
        return authRepository.save(userEntity);
    }
}


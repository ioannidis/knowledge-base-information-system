package eu.ioannidis.vks.userservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import eu.ioannidis.vks.userservice.models.entities.AuthorityEntity;
import eu.ioannidis.vks.userservice.models.entities.UserEntity;
import eu.ioannidis.vks.userservice.models.entities.embeddablekeys.AuthorityKey;
import eu.ioannidis.vks.userservice.models.requests.CreateUserRequest;
import eu.ioannidis.vks.userservice.models.requests.UpdateUserRequest;
import eu.ioannidis.vks.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    final static String BASE_URL = "/v1/users";

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping
//    @HystrixCommand(
//            fallbackMethod = "getUsersFallback",
//            commandProperties = {
//                    // Default is 1000 ms
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//            }
//    )
    @GetMapping
    public ResponseEntity<Collection<UserEntity>> getUsers() {
        List<UserEntity> users = userService.findAll();
        System.out.println(users);
        return new ResponseEntity<Collection<UserEntity>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String id) {
        Optional<UserEntity> user = userService.findById(UUID.fromString(id));

        return user
                .map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<UserEntity> saveUser(@RequestBody CreateUserRequest createUserRequest) {

        UserEntity user = new UserEntity(
                createUserRequest.getUsername(),
                createUserRequest.getEmail(),
                passwordEncoder.encode(createUserRequest.getPassword()),
                createUserRequest.isEnabled()
                );

        Set<AuthorityEntity> authorities = new HashSet<>();

        for (String authority : createUserRequest.getAuthorities()) {
            AuthorityEntity authorityEntity = new AuthorityEntity( new AuthorityKey(authority));
//            AuthorityEntity authorityEntity = new AuthorityEntity(authority);
            authorityEntity.setUser(user);
            authorities.add(authorityEntity);
        }

        user.setAuthorities(authorities);

        user.getAuthorities().forEach(x -> System.out.println(x));

        user = userService.save(user);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }

    @PutMapping ()
    ResponseEntity<UserEntity> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        UUID userId = UUID.fromString(updateUserRequest.getId());
        Optional<UserEntity> user = userService.findById(userId);

        return user
                .map(userEntity -> {
                    userEntity.setEmail(updateUserRequest.getEmail());
                    userEntity.setUsername(updateUserRequest.getUsername());
                    userEntity.setEnabled(updateUserRequest.isEnabled());

                    if (updateUserRequest.getPassword() != null) {
                        userEntity.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
                    }

                    Set<AuthorityEntity> authorities = new HashSet<>();

                    for (String authority : updateUserRequest.getAuthorities()) {
                        AuthorityEntity authorityEntity = new AuthorityEntity( new AuthorityKey(userEntity.getId(), authority));
//            AuthorityEntity authorityEntity = new AuthorityEntity(user.getId(), authority);
                        authorityEntity.setUser(userEntity);
                        authorities.add(authorityEntity);
                    }

                    userEntity.setAuthorities(authorities);

                    return new ResponseEntity<>(userService.save(userEntity), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> patchUser(@PathVariable String id, @RequestBody Map<String, Object> body) {
        Map<String, Object> jsonObj = body;

        Optional<UserEntity> user = userService.findById(UUID.fromString(id));

        return user
                .map(userEntity -> {
                    userEntity.setEnabled((Boolean) jsonObj.get("enabled"));
                    UserEntity patchedUser = this.userService.save(userEntity);

                    return new ResponseEntity<>(patchedUser, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
    }


    private ResponseEntity<Collection<UserEntity>> getUsersFallback() {
        System.out.println("Fallback CALLED!!!");
        List<UserEntity> u = new ArrayList<>();
        u.add(new UserEntity(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"), "no-info" ,"no-info", "no-info", true));
        return new ResponseEntity<Collection<UserEntity>>(u, HttpStatus.OK);
    }

}

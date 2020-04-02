package eu.ioannidis.vks.userservice.feignclients;

import eu.ioannidis.vks.userservice.models.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("userservice")
public interface UserFeignClient {

    @GetMapping("/v1/users")
    List<UserModel> getUsers();

    @GetMapping("/v1/users/{id}")
    UserModel getUser(@PathVariable String id);

}

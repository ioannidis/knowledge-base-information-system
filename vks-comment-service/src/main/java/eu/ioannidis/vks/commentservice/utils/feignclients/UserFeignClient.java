package eu.ioannidis.vks.commentservice.utils.feignclients;

import eu.ioannidis.vks.commentservice.models.entities.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("userservice")
public interface UserFeignClient {
    @GetMapping("/v1/users/{id}")
    ResponseEntity<UserEntity> getUser(@PathVariable String id, @RequestHeader("Authorization") String bearerToken);
}

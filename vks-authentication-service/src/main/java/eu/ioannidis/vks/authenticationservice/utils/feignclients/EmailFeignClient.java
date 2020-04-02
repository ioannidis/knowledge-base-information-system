package eu.ioannidis.vks.authenticationservice.utils.feignclients;

import eu.ioannidis.vks.authenticationservice.models.EmailModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "emailservice")
public interface EmailFeignClient {

    @PostMapping(value = "/v1/email/send")
    void sendEmail(@RequestBody EmailModel emailModel);
}

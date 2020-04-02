package eu.ioannidis.vks.emailservice.controllers;

import eu.ioannidis.vks.emailservice.models.requests.EmailSendRequest;
import eu.ioannidis.vks.emailservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EmailController.BASE_URL)
public class EmailController {

    public static final String BASE_URL = "/v1/email";

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailSendRequest emailSendRequest) {
        emailService.sendEmail(
                emailSendRequest.getSender(),
                emailSendRequest.getReceiver(),
                emailSendRequest.getSubject(),
                emailSendRequest.getBody()
        );
    }

}

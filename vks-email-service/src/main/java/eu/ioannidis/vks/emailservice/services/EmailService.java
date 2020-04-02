package eu.ioannidis.vks.emailservice.services;

import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(String sender, String receiver, String subject, String body);

}

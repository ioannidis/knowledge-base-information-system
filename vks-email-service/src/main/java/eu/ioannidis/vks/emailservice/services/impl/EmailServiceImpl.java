package eu.ioannidis.vks.emailservice.services.impl;

import eu.ioannidis.vks.emailservice.services.EmailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String SMTP_SERVER = "10.130.128.30";

    @Override
    public void sendEmail(String sender, String receiver, String subject, String body) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.auth", "false");
        prop.put("mail.smtp.port", "25");

        Session session = Session.getInstance(prop, null);

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

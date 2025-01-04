package com.hay.user.registrationservices.service;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendWelcomeEmail(String toEmail, String username) {
        try{
        // Create the email message
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("no-reply@example.com");
            message.setTo(toEmail);
            message.setSubject("Welcome to Our Service!");
            message.setText("Dear " + username + ",\n\n" +
                            "Congratulations, your account has been successfully created." +
                            "Best Regards,\nYour Company");

            // Send the email
            javaMailSender.send(message);
            System.out.println("Welcome email sent to: " + toEmail);
        } catch (MailSendException e) {
            log.error("Mail server is down: " + e.getMessage());
        }
    }
}

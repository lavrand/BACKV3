package com.cvbank.service;

import com.cvbank.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    private JavaMailSender javaMailSender;


    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendNotification(Profile profile) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(profile.getEmail());
        mail.setSubject("Registration on Global CV Bank Website");
        mail.setText("Thank you for registering with Global CV Bank!");

        javaMailSender.send(mail);

    }
}

package com.mohdasha.security.listener;

import com.mohdasha.security.event.UserRegistrationEvent;
import com.mohdasha.security.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired private VerificationService verificationService;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        String username = event.getUserRegistrationDto().getUsername();
        String verificationId = verificationService.createVerification(username);
        String email = event.getUserRegistrationDto().getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Productive.io Account Verification");
        simpleMailMessage.setText("Account verification link: https://localhost:8200/verify/email?id="+verificationId);
        simpleMailMessage.setTo(email);
        javaMailSender.send(simpleMailMessage);
    }
}

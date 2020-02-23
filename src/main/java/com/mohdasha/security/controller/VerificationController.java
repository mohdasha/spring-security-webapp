package com.mohdasha.security.controller;

import com.mohdasha.security.entity.User;
import com.mohdasha.security.entity.Verification;
import com.mohdasha.security.repository.UserRepository;
import com.mohdasha.security.service.EmailVerificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/verify")
public class VerificationController {

    @Autowired private EmailVerificationServiceImpl verificationService;
    @Autowired private UserRepository userRepository;

    @GetMapping("/email")
    public String verifyEmail(@RequestParam("id") String id) {
        Verification verification =  verificationService.getVerification(id);

        if(verification != null) {
            User user = userRepository.findByUsername(verification.getUsername());
            user.setVerified(true);
            userRepository.save(user);
        }
        return "redirect:/login-verified";
    }

}

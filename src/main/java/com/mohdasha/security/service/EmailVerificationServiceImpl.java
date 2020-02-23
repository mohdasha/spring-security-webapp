package com.mohdasha.security.service;

import com.mohdasha.security.entity.Verification;
import com.mohdasha.security.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailVerificationServiceImpl implements VerificationService {

    @Autowired private UserAccountService userAccountService;
    @Autowired private VerificationRepository verificationRepository;

    @Override
    public String createVerification(String username) {
        Verification verification = new Verification(username);
        return verificationRepository.save(verification).getVerificationId();
    }

    public Verification getVerification(String id) {
        Optional<Verification> verification = verificationRepository.findById(id);
        return verification.orElseGet(null);
    }

}

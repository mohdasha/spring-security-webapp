package com.mohdasha.security.service;

import com.mohdasha.security.entity.TOTPDetails;
import com.mohdasha.security.entity.User;
import com.mohdasha.security.repository.TOTPDetailsRepository;
import com.mohdasha.security.repository.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class TOTPQueryService {
    @Autowired private UserRepository userRepository;
    @Autowired private TOTPDetailsRepository totpDetailsRepository;
    @Autowired private GoogleAuthenticator googleAuthenticator;

    public static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    private static String APP_NAME = "productive.io";

    public boolean isTotpEnabled(String username) {
        User user = userRepository.findByUsername(username);

        return user.isTotpEnabled();
    }

    public String generateNewGoogleAuthQRURL(String username) throws UnsupportedEncodingException {
        TOTPDetails totpDetails = totpDetailsRepository.findByUsername(username);
        return generateQRUrl(totpDetails);
    }

    private String generateQRUrl(TOTPDetails totpDetails) throws UnsupportedEncodingException {
        return QR_PREFIX + URLEncoder.encode(String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                APP_NAME, totpDetails.getUsername(), totpDetails.getSecret(), APP_NAME),
                "UTF-8");
    }
}

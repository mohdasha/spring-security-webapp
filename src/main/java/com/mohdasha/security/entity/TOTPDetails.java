package com.mohdasha.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TOTPDetails {
    @Id private String totpId;
    @Indexed(unique = true)
    private String username;
    private String secret;

    public TOTPDetails(String username, String secret) {
        this.username = username;
        this.secret = secret;
    }

    public String getTotpId() {
        return totpId;
    }

    public String getUsername() {
        return username;
    }

    public String getSecret() {
        return secret;
    }
}

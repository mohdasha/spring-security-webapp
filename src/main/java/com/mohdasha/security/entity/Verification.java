package com.mohdasha.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Verification {

    @Id private String verificationId;

    @Indexed(name = "username_idx", direction = IndexDirection.ASCENDING, unique = true)
    private String username;

    public Verification(String username) {
        this.username = username;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public String getUsername() {
        return username;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

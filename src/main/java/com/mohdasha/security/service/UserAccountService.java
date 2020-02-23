package com.mohdasha.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    String isVerified(String username);
}

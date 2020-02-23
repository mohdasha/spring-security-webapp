package com.mohdasha.security.service;

import com.mohdasha.security.entity.User;
import com.mohdasha.security.model.UserRegistrationDto;
import com.mohdasha.security.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class NoSQLUserDetailsServiceImpl implements UserAccountService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername(username);
		User result = userRepo.findByUsername(username);

		if(result == null)
			throw new UsernameNotFoundException("Username not found!!");

		return org.springframework.security.core.userdetails.User.withUsername(result.getUsername())
				.password(result.getPassword()).roles("USER").disabled(!result.isVerified()).build();
	}

    public String createUser(UserRegistrationDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setSecurityPin(passwordEncoder.encode(userDto.getSecurityPin()));
        user = userRepo.save(user);

        return user.getUserId();
    }

    public String isVerified(String username) {
		return UUID.randomUUID().toString();
	}
	
}

package com.mohdasha.security.service;

import com.mohdasha.security.entity.User;
import com.mohdasha.security.model.Authorities;
import com.mohdasha.security.model.UserRegistrationDto;
import com.mohdasha.security.repository.UserRepository;
import com.mohdasha.security.userdetails.MFAUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class NoSQLUserDetailsServiceImpl implements UserAccountService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;

	private static boolean DEFAULT_ACC_NON_EXP = true;
	private static boolean DEFAULT_CRED_NON_EXP = true;
	private static boolean DEFAULT_ACC_NON_LOCKED = true;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setUsername(username);
		User result = userRepo.findByUsername(username);

		if(result == null)
			throw new UsernameNotFoundException("Username not found!!");

		List<String> roles = new ArrayList<>();

		if(result.isTotpEnabled())
			roles.add(Authorities.TOTP_AUTH_AUTHORITY);
		else
			roles.add(Authorities.ROLE_USER);

		MFAUser mfaUser = new MFAUser(result.getUsername(),
				result.getPassword(),
				true,
				DEFAULT_ACC_NON_EXP,
				DEFAULT_CRED_NON_EXP,
				DEFAULT_ACC_NON_LOCKED,
				buildAuthorities(roles)
				);
		mfaUser.setSecurityPin(result.getSecurityPin());

		return mfaUser;
	}

	public List<GrantedAuthority> buildAuthorities(List<String> roles) {
		List<GrantedAuthority> authorityList = new ArrayList<>(1);

		for(String role: roles) {
			authorityList.add(new SimpleGrantedAuthority(role));
		}

		return authorityList;
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

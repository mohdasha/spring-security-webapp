package com.mohdasha.security.controller;

import com.mohdasha.security.event.UserRegistrationEvent;
import com.mohdasha.security.model.UserLoginDto;
import com.mohdasha.security.service.NoSQLUserDetailsServiceImpl;
import com.mohdasha.security.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohdasha.security.model.UserRegistrationDto;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

	@Autowired private NoSQLUserDetailsServiceImpl userAccountService;
	@Autowired private VerificationService verificationService;
	@Autowired private ApplicationEventPublisher applicationEventPublisher;


	@GetMapping("/register")
	public String showSignUpForm(UserRegistrationDto userRegistrationDto) {
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid UserRegistrationDto userRegistrationDto, BindingResult result) {
		if(result.hasErrors())
			return "register";

		userAccountService.createUser(userRegistrationDto);
//		applicationEventPublisher.publishEvent(new UserRegistrationEvent(userRegistrationDto));
		return "redirect:register?success.html";
	}

	@GetMapping("/login")
	public String loginPage(UserLoginDto userLoginDto) {
		return "login";
	}

	@PostMapping("/home")
	public String home(UserLoginDto userLoginDto, BindingResult result) {
		return "success";
	}
}

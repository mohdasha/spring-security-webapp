package com.mohdasha.security.controller;

import com.mohdasha.security.event.UserRegistrationEvent;
import com.mohdasha.security.model.UserLoginDto;
import com.mohdasha.security.service.NoSQLUserDetailsServiceImpl;
import com.mohdasha.security.service.TOTPQueryService;
import com.mohdasha.security.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohdasha.security.model.UserRegistrationDto;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

	@Autowired private NoSQLUserDetailsServiceImpl userAccountService;
	@Autowired private VerificationService verificationService;
	@Autowired private ApplicationEventPublisher applicationEventPublisher;
	@Autowired private TOTPQueryService totpQueryService;

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
		return "redirect:register?success";
	}

	@GetMapping("/login")
	public String loginPage(UserLoginDto userLoginDto) {
		return "login";
	}

	@PostMapping("/home")
	public String home(UserLoginDto userLoginDto, BindingResult result) {
		return "success";
	}

	@GetMapping("/authenticator-url")
	public String getGoogleAuthenticatorQRURL(Model model, Principal principal) throws UnsupportedEncodingException {
		boolean userHasTOTPEnabled = totpQueryService.isTotpEnabled(principal.getName());

		if(!userHasTOTPEnabled) {
			String qrURL = totpQueryService.generateNewGoogleAuthQRURL(principal.getName());
			model.addAttribute("qrURL", qrURL);
		}
		model.addAttribute("totpEnabled", userHasTOTPEnabled);
		return "account";
	}
	@GetMapping("/authenticator")
	public String getAccount(Model model, Principal principal) {
		model.addAttribute("totpEnabled", totpQueryService.isTotpEnabled(principal.getName()));
		return "account";
	}
}

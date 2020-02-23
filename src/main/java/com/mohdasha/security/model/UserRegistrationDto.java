package com.mohdasha.security.model;

import com.mohdasha.security.validator.annotation.PasswordConfirmed;
import com.mohdasha.security.validator.annotation.PasswordPolicy;
import com.mohdasha.security.validator.annotation.UniqueEmail;
import com.mohdasha.security.validator.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordConfirmed
public class UserRegistrationDto {

	@NotNull
	@NotEmpty(message = "Username cannot be empty")
	@UniqueUsername
	private String username;

	@PasswordPolicy
	private String password;
	private String confirmPassword;

	@NotEmpty(message = "First Name cannot be empty")
	private String firstName;
	@NotEmpty(message = "Last Name cannot be empty")
	private String lastName;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email")
	@UniqueEmail
	private String email;

	@Min(4)
	private String securityPin;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSecurityPin() {
		return securityPin;
	}

	public void setSecurityPin(String securityPin) {
		this.securityPin = securityPin;
	}
}

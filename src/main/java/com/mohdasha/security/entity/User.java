package com.mohdasha.security.entity;


import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	@Id
	private String userId;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private boolean verified;
	private String securityPin;
	private boolean totpEnabled;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getSecurityPin() {
		return securityPin;
	}

	public void setSecurityPin(String securityPin) {
		this.securityPin = securityPin;
	}

	public boolean isTotpEnabled() {
		return totpEnabled;
	}

	public void setTotpEnabled(boolean totpEnabled) {
		this.totpEnabled = totpEnabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return email != null ? email.equals(user.email) : user.email == null;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId='" + userId + '\'' +
				", username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}

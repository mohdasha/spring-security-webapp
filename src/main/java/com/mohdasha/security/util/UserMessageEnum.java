package com.mohdasha.security.util;

public enum UserMessageEnum {
	USER_ID_NOT_ALLOWED("User Id not allowed while creating user");
	
	private String message;
	
	UserMessageEnum(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.message;
	}
	
	
}

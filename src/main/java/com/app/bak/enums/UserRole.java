package com.app.bak.enums;

public enum UserRole {

	ADMIN("Admin"), USER("User");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String getCode() {
		return role;
	}

}

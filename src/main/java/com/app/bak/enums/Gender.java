package com.app.bak.enums;

public enum Gender {
	
	MALE("Male"), FEMALE("Female");

	private String gender;

	private Gender(String gender) {
		this.gender = gender;
	}

	public String getCode() {
		return gender;
	}
}

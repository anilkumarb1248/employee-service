package com.app.bak.enums;

public enum AccessType {

	ADMIN("Admin"), ADD("Add"), VIEW("View"), EDIT("Edit"), DELETE("Delete");

	private String type;

	private AccessType(String type) {
		this.type = type;
	}

	public String getCode() {
		return type;
	}
}

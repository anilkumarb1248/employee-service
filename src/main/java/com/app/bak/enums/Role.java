package com.app.bak.enums;

public enum Role {
	SE("Software Engineer"),
	SSE("Senior Software Engineer"), 
	TA("Technology Analyst"), 
	LEAD("Lead"), 
	MANAGER("Manager"), 
	CEO("CEO");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	public String getCode() {
		return role;
	}
}

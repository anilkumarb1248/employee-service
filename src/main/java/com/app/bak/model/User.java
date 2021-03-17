package com.app.bak.model;

import java.io.Serializable;
import java.util.List;

import com.app.bak.enums.AccessType;

/**
 * @author anilb
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String userId;
	private String fullName;
	private String password;
	private String email;
	private int mobileNumber;
	private List<AccessType> accessTypes;

	public User() {
		super();
	}

	/**
	 * @param id
	 * @param userId
	 * @param fullName
	 * @param password
	 * @param email
	 * @param mobileNumber
	 * @param accessTypes
	 */
	public User(int id, String userId, String fullName, String password, String email, int mobileNumber,
			List<AccessType> accessTypes) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accessTypes = accessTypes;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobileNumber
	 */
	public int getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the accessTypes
	 */
	public List<AccessType> getAccessTypes() {
		return accessTypes;
	}

	/**
	 * @param accessTypes the accessTypes to set
	 */
	public void setAccessTypes(List<AccessType> accessTypes) {
		this.accessTypes = accessTypes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", fullName=" + fullName + ", password=" + password
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", accessTypes=" + accessTypes + "]";
	}

}

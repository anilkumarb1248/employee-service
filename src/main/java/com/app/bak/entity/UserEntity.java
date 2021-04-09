package com.app.bak.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.bak.enums.AccessType;

@Entity
@Table(name = "USER")
public class UserEntity  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(initialValue = 1, name = "user_seq", sequenceName = "USER_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private int id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

//	@Column(name = "ACCESS_TYPES")
	@ElementCollection
	@JoinTable(name="ACCESS_TYPES")
	private List<AccessType> accessTypes;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
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

}

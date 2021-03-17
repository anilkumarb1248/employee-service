package com.app.bak.model;

import java.io.Serializable;

/**
 * @author anilb
 *
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String password;
	private boolean keepLogin;

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
	 * @return the keepLogin
	 */
	public boolean isKeepLogin() {
		return keepLogin;
	}

	/**
	 * @param keepLogin the keepLogin to set
	 */
	public void setKeepLogin(boolean keepLogin) {
		this.keepLogin = keepLogin;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", password=" + password + ", keepLogin=" + keepLogin + "]";
	}

}

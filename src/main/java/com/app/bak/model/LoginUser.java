package com.app.bak.model;

import java.io.Serializable;

/**
 * @author anilb
 *
 */
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private boolean keepLogin;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
		return "LoginUser [userName=" + userName + ", password=" + password + ", keepLogin=" + keepLogin + "]";
	}

}

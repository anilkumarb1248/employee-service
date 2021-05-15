package com.app.bak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.bak.model.LoginUser;
import com.app.bak.model.User;
import com.app.bak.service.UserService;
import com.app.bak.util.ResponseStatus;

@RestController
public class LoginResourceImpl implements LoginResource {

	@Autowired
	UserService userService;

	@Override
	public ResponseStatus authenticateUser(LoginUser loginUser) {
		ResponseStatus status = new ResponseStatus();

		User user = userService.getUserByUserName(loginUser.getUserName());
		if (null == user) {
			status.setStatusCode("404");
			status.setErrorMessage("User not found or Incorrect UserId");
		} else {
			if (user.getPassword().equals(loginUser.getPassword())) {
				status.setStatusCode("200");
				status.setMessage("User Exist");
			} else {
				status.setStatusCode("404");
				status.setErrorMessage("Incorrect Password");
			}
		}
		return status;
	}

	@Override
	public ResponseStatus logoutUser(LoginUser loginUser) {
		ResponseStatus status = new ResponseStatus();
		status.setStatusCode("200");
		status.setMessage("User Exist");
		return status;
	}

}

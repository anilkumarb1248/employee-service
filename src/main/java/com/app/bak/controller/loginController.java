package com.app.bak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bak.model.LoginUser;
import com.app.bak.model.ResponseStatus;
import com.app.bak.model.User;
import com.app.bak.service.UserService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class loginController {

	@Autowired
	UserService userService;

	@PostMapping("/authenticate")
	public ResponseStatus authenticateUser(@RequestBody LoginUser loginUser) {
		ResponseStatus status = new ResponseStatus();
		User user = userService.getUserByUserId(loginUser.getUserId());
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

}

package com.app.bak.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.bak.model.LoginUser;
import com.app.bak.util.ResponseStatus;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
public interface LoginResource {

	@PostMapping("/login")
	public ResponseStatus authenticateUser(@RequestBody LoginUser loginUser);

	@PostMapping("/logout")
	public ResponseStatus logoutUser(@RequestBody LoginUser loginUser);


}

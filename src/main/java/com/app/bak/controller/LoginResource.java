package com.app.bak.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.bak.model.LoginUser;
import com.app.bak.util.ResponseStatus;

@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public interface LoginResource {

	@PostMapping("/authenticate")
	public ResponseStatus authenticateUser(@RequestBody LoginUser loginUser);

}

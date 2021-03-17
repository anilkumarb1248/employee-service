package com.app.bak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bak.model.ResponseStatus;
import com.app.bak.model.User;
import com.app.bak.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getUserList")
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@GetMapping("/getUser/{id}")
	public User getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@GetMapping("/getUserByUserId/{userId}")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(userId);
	}

	@PostMapping("/addUser")
	public ResponseStatus addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PutMapping("/updateUser")
	public ResponseStatus updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseStatus deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@GetMapping("/refreshUserList")
	public ResponseStatus refreshUserList() {
		ResponseStatus status = new ResponseStatus();
		userService.refreshUserList();
		status.setStatusCode("200");
		status.setMessage("Done");
		return status;
	}

}

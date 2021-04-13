package com.app.bak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.bak.model.User;
import com.app.bak.service.UserService;
import com.app.bak.util.ResponseStatus;

@RestController
public class UserResourceImpl implements UserResource {

	@Autowired
	UserService userService;

	@Override
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@Override
	public User getUser(int id) {
		return userService.getUser(id);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userService.getUserByUserName(userName);
	}

	@Override
	public ResponseStatus addUser(User user) {
		return userService.addUser(user);
	}

	@Override
	public ResponseStatus updateUser(User user) {
		return userService.updateUser(user);
	}

	@Override
	public ResponseStatus deleteUser(int id) {
		return userService.deleteUser(id);
	}

	@Override
	public ResponseStatus deleteUserByUserName(String userName) {
		return userService.deleteUserByUsername(userName);
	}

}

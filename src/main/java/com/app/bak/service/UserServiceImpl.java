package com.app.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bak.data.UserDataHandler;
import com.app.bak.model.ResponseStatus;
import com.app.bak.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDataHandler userDataHandler;

	@Override
	public List<User> getUserList() {
		return userDataHandler.getUserList();
	}

	@Override
	public User getUser(int id) {
		return userDataHandler.getUser(id);
	}
	
	@Override
	public User getUserByUserId(String userId) {
		return userDataHandler.getUserByUserId(userId);
	}

	@Override
	public ResponseStatus addUser(User user) {
		return userDataHandler.addUser(user);
	}

	@Override
	public ResponseStatus updateUser(User user) {
		return userDataHandler.updateUser(user);
	}

	@Override
	public ResponseStatus deleteUser(int id) {
		return userDataHandler.deleteUser(id);
	}

	@Override
	public void refreshUserList() {
		userDataHandler.refreshEmployeeList();
	}

}

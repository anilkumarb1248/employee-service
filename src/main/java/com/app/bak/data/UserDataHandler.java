package com.app.bak.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.bak.enums.AccessType;
import com.app.bak.model.ResponseStatus;
import com.app.bak.model.User;

@Component("userDataHandler")
public class UserDataHandler {

	private static int autoIncreamentUserId;
	private static Map<Integer, User> USER_DATA_MAP = new HashMap<>();

	static {
		loadData();
	}

	private static void loadData() {
		autoIncreamentUserId = 100;
		USER_DATA_MAP.put(autoIncreamentUserId, new User(autoIncreamentUserId++, "anil", "Anil Kumar Bandari", "anil",
				"anil@gmail.com", 12345, getAccessTypes()));
		USER_DATA_MAP.put(autoIncreamentUserId, new User(autoIncreamentUserId++, "manasvi", "Manasvi Bandari",
				"manasvi", "manasvi@gmail.com", 654789, getAccessTypes()));
	}

	private static List<AccessType> getAccessTypes() {
		List<AccessType> list = new ArrayList<>();
		list.add(AccessType.Admin);
		return list;
	}

	public List<User> getUserList() {
		return new ArrayList<User>(USER_DATA_MAP.values());
	}

	public User getUserByUserId(String userId) {
		List<User> users = new ArrayList<User>(USER_DATA_MAP.values());
		return users.stream().filter(user -> userId.equalsIgnoreCase(user.getUserId())).findAny().orElse(null);
	}

	public User getUser(int id) {
		return USER_DATA_MAP.get(id);
	}

	public ResponseStatus addUser(User user) {
		ResponseStatus status = new ResponseStatus();

		if (!USER_DATA_MAP.containsKey(user.getId())) {
			user.setId(autoIncreamentUserId);
			USER_DATA_MAP.put(autoIncreamentUserId++, user);

			status.setMessage("User added successfully");
			status.setStatusCode("200");
		} else {
			status.setErrorMessage("Failed to add user");
			status.setStatusCode("400");
		}

		return status;
	}

	public ResponseStatus updateUser(User user) {
		ResponseStatus status = new ResponseStatus();
		if (USER_DATA_MAP.containsKey(user.getId())) {
			USER_DATA_MAP.put(user.getId(), user);
			status.setMessage("User updated successfully with id: " + user.getId());
			status.setStatusCode("200");
		} else {
			status.setErrorMessage("Failed to update user with id: " + user.getId());
			status.setStatusCode("400");
		}
		return status;
	}

	public ResponseStatus deleteUser(int id) {
		ResponseStatus status = new ResponseStatus();
		if (USER_DATA_MAP.containsKey(id)) {
			USER_DATA_MAP.remove(id);
			status.setMessage("User deleted successfully with id: " + id);
			status.setStatusCode("200");
		} else {
			status.setErrorMessage("Failed to delete user with id: " + id);
			status.setStatusCode("400");
		}
		return status;
	}

	public void refreshEmployeeList() {
		loadData();
	}

}

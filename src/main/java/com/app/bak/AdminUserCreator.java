package com.app.bak;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.bak.enums.AccessType;
import com.app.bak.model.User;
import com.app.bak.service.UserService;
import com.app.bak.util.ResponseStatus;

@Component
public class AdminUserCreator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserCreator.class);

	@Autowired
	UserService userService;

	public void createAdminUser() {
		List<AccessType> accessTypes = new ArrayList<>();
		accessTypes.add(AccessType.ADMIN);

		User user = new User(1, "admin", "Admin", "admin", "admin@gmail.com", "9700346588", accessTypes);
		ResponseStatus responseStatus = userService.addUser(user);
		if("201".equals(responseStatus.getStatusCode())) {
			LOGGER.info("Admin user added successfully");
		}else {
			LOGGER.error("Failied to add Admin user");
		}
		
	}

}

package com.app.bak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.bak.enums.UserRole;
import com.app.bak.exceptions.DuplicateUserException;
import com.app.bak.model.User;
import com.app.bak.service.UserService;
import com.app.bak.util.ResponseStatus;

@Component
public class AdminUserCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserCreator.class);

	@Autowired
	UserService userService;

	/**
	 * Creating Admin user to login in Application UI with userid: admin, pwd: admin
	 */
	public void createAdminUser() {

		try {
			User user = new User();
			user.setUserName("admin");
			user.setPassword("admin");
			user.setEmail("admin@gmail.com");
			user.setMobileNumber("9700346588");
			user.setUserRole(UserRole.ADMIN);
			user.setActive(true);
			user.setAccountExpired(false);
			user.setAccountLocked(false);
			user.setCredentialsExpired(false);

			ResponseStatus responseStatus = userService.addUser(user);
			if ("201".equals(responseStatus.getStatusCode())) {
				LOGGER.info("Admin user added successfully");
			} else {
				LOGGER.error("Failied to add Admin user");
			}
		} catch (DuplicateUserException ex) {
			LOGGER.info("Admin user already existed");
		} catch (Exception ex) {
			LOGGER.info("Exception occurred while adding Admin user");
		}

	}

}

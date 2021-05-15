package com.app.bak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.bak.enums.UserRole;
import com.app.bak.model.User;
import com.app.bak.service.UserService;

@Component
public class UsersCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersCreator.class);

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Creating users for first login purpose
	 */
	public void createUsers() {

		try {
			if (!userService.checkUserAlreadyExist(null, "admin")) {
				User admin = new User();
				admin.setUserName("admin");
				// admin.setPassword("admin");
				admin.setPassword(passwordEncoder.encode("admin"));
				admin.setEmail("admin@gmail.com");
				admin.setMobileNumber("1234567890");
				admin.setUserRole(UserRole.ADMIN);
				admin.setActive(true);
				admin.setAccountExpired(false);
				admin.setAccountLocked(false);
				admin.setCredentialsExpired(false);
				
				userService.addUser(admin);
			}

			if (!userService.checkUserAlreadyExist(null, "anil")) {
				User user = new User();
				user.setUserName("anil");
				// user.setPassword("anil");
				user.setPassword(passwordEncoder.encode("anil"));
				user.setEmail("anil@gmail.com");
				user.setMobileNumber("9876543210");
				user.setUserRole(UserRole.USER);
				user.setActive(true);
				user.setAccountExpired(false);
				user.setAccountLocked(false);
				user.setCredentialsExpired(false);
				
				userService.addUser(user);
			}
		} catch (Exception ex) {
			LOGGER.info("Exception occurred while adding the usersr");
		}
	}

}

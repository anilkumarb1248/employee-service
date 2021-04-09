package com.app.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication implements ApplicationRunner {

	@Autowired
	AdminUserCreator adminUserCreator;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		adminUserCreator.createAdminUser();
	}

}

package com.app.bak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.app.bak.service.EmployeeService;

@SpringBootApplication
@EnableCaching
public class ServiceApplication implements ApplicationRunner {

	@Autowired
	UsersCreator userCreator;
	
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Setup or initialization activities
		userCreator.createUsers();
		testProcedure();
	}
	
	public void testProcedure() {
//		employeeService.callStoredProcedure();
	}

}

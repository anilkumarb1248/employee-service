package com.app.bak.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.bak.model.User;
import com.app.bak.util.ResponseStatus;


//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
//@RolesAllowed("ADMIN")
//@Secured("ADMIN") // it works with only @EnableGlobalMethodSecurity(secureEnabled = true) in config
@RequestMapping("/user")
public interface UserResource {

	@GetMapping({ "/list", "/all", "/users" })
	public List<User> getUserList();

	@GetMapping("/get/{id}")
	public User getUser(@PathVariable int id);

	@GetMapping("/getUserByUserName/{userName}")
	public User getUserByUserName(@PathVariable String userName);

	@PostMapping("/add")
	public ResponseStatus addUser(@RequestBody User user);

	@PutMapping("/update")
	public ResponseStatus updateUser(@RequestBody User user);

	@DeleteMapping("/delete/{id}")
	public ResponseStatus deleteUser(@PathVariable int id);
	
	@DeleteMapping("/deleteUserByUserName/{userName}")
	public ResponseStatus deleteUserByUserName(@PathVariable String userName);

}

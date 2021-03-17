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

import com.app.bak.model.Employee;
import com.app.bak.model.ResponseStatus;
import com.app.bak.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/getEmployeeList")
	public List<Employee> getEmployeeList() {
		return employeeService.getEmployeeList();
	}

	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping("/addEmployee")
	public ResponseStatus addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/updateEmployee")
	public ResponseStatus updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseStatus deleteEmployee(@PathVariable int id) {
		return employeeService.deleteEmployee(id);
	}
	
	@GetMapping("/refreshEmployeeList")
	public ResponseStatus refreshEmployeeList() {
		ResponseStatus status = new ResponseStatus();
		employeeService.refreshEmployeeList();
		status.setStatusCode("200");
		status.setMessage("Done");
		return status;
	}

}

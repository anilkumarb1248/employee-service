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
import org.springframework.web.bind.annotation.RequestParam;

import com.app.bak.model.Employee;
import com.app.bak.util.ResponseStatus;

@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public interface EmployeeResource {

	/*
	 * It will handle any request among three uri's
	 * getEmployeeList
	 * http://localhost:2021/EmployeeManagement/employee/list
	 * http://localhost:2021/EmployeeManagement/employee/all
	 * http://localhost:2021/EmployeeManagement/employee/employees
	 * 
	 */
	@GetMapping({"/list","/all","/employees"})
	public List<Employee> getEmployeeList();

	@GetMapping("/get/{id}")
	public Employee getEmployee(@PathVariable(value="id") int id);

	@PostMapping("/add")
	public ResponseStatus addEmployee(@RequestBody Employee employee);
	
	@PostMapping("/addAll")
	public ResponseStatus addEmployees(@RequestBody List<Employee> employees);

	@PutMapping("/update")
	public ResponseStatus updateEmployee(@RequestBody Employee employee);

	@DeleteMapping("/delete/{id}")
	public ResponseStatus deleteEmployee(@PathVariable int id);
	
	@GetMapping("/findByName")
	public Employee findEmployee(@RequestParam String name);

}

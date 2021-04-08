package com.app.bak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.app.bak.model.Employee;
import com.app.bak.service.EmployeeService;
import com.app.bak.util.ResponseStatus;

@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	@Autowired
	EmployeeService employeeService;

	@Override
	public List<Employee> getEmployeeList() {
		return employeeService.getEmployeeList();
	}

	@Override
	public Employee getEmployee(int id) {
		return employeeService.getEmployee(id);
	}

	@Override
	public ResponseStatus addEmployee(Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@Override
	public ResponseStatus addEmployees(List<Employee> employees) {
		return null;
	}

	@Override
	public ResponseStatus updateEmployee(Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	@Override
	public ResponseStatus deleteEmployee(int id) {
		return employeeService.deleteEmployee(id);
	}

	@Override
	public Employee findEmployee(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

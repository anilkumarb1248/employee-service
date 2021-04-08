package com.app.bak.service;

import java.util.List;

import com.app.bak.model.Employee;
import com.app.bak.util.ResponseStatus;

public interface EmployeeService {
	
	public List<Employee> getEmployeeList();

	public Employee getEmployee(int id);

	public ResponseStatus addEmployee(Employee employee);
	
	public ResponseStatus addEmployees(List<Employee> employees);

	public ResponseStatus updateEmployee(Employee employee);

	public ResponseStatus deleteEmployee(int id);
	
}

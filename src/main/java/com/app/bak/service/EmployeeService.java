package com.app.bak.service;

import java.util.List;

import com.app.bak.model.Employee;
import com.app.bak.model.ResponseStatus;

public interface EmployeeService {
	
	public List<Employee> getEmployeeList();

	public Employee getEmployee(int id);

	public ResponseStatus addEmployee(Employee employee);

	public ResponseStatus updateEmployee(Employee employee);

	public ResponseStatus deleteEmployee(int id);
	
	public void refreshEmployeeList();
}

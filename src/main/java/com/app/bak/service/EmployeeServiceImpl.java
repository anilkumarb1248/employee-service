package com.app.bak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bak.data.EmployeeDataHandler;
import com.app.bak.model.Employee;
import com.app.bak.model.ResponseStatus;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDataHandler employeeDataHandler;

	@Override
	public List<Employee> getEmployeeList() {
		return employeeDataHandler.getEmployeeList();
	}

	@Override
	public Employee getEmployee(int id) {
		return employeeDataHandler.getEmployee(id);
	}

	@Override
	public ResponseStatus addEmployee(Employee employee) {
		return employeeDataHandler.addEmployee(employee);
	}

	@Override
	public ResponseStatus updateEmployee(Employee employee) {
		return employeeDataHandler.updateEmployee(employee);
	}

	@Override
	public ResponseStatus deleteEmployee(int id) {
		return employeeDataHandler.deleteEmployee(id);
	}

	@Override
	public void refreshEmployeeList() {
		employeeDataHandler.refreshEmployeeList();
	}
}

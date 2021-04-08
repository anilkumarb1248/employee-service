package com.app.bak.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.bak.entity.EmployeeEntity;
import com.app.bak.exceptions.DuplicateEmployeeException;
import com.app.bak.exceptions.EmployeeNotFoundException;
import com.app.bak.model.Employee;
import com.app.bak.repository.EmployeeRepository;
import com.app.bak.util.ResponseStatus;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployeeList() {
		List<EmployeeEntity> entitiesList = employeeRepository.findAll();

		List<Employee> employeesList = new ArrayList<>();

		entitiesList.stream().forEach(emp -> {
			Employee employee = new Employee();
			BeanUtils.copyProperties(emp, employee);
			employeesList.add(employee);
		});
		return employeesList;

	}

	@Override
	public Employee getEmployee(int id) {
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new EmployeeNotFoundException("No employee found with id:" + id);
		}

		EmployeeEntity employeeEntity = optional.get();
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeEntity, employee);

		return employee;

	}

	@Override
	public ResponseStatus addEmployee(Employee employee) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<EmployeeEntity> optional = employeeRepository.findByFirstName(employee.getFirstName());
		if (optional.isPresent()) {
			throw new DuplicateEmployeeException("Employee already exist with name: " + employee.getFirstName());
		}

		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		EmployeeEntity emp = employeeRepository.save(employeeEntity);
		if (null != emp) {
			responseStatus.setStatusCode(String.valueOf(HttpStatus.CREATED.value()));
			responseStatus.setMessage("Employee added successfully");
		} else {
			responseStatus.setStatusCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value())); // UNPROCESSABLE_ENTITY - 422
			responseStatus.setMessage("Failed to add Employee");
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus addEmployees(List<Employee> employees) {
		ResponseStatus responseStatus = new ResponseStatus();
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		employees.stream().forEach(emp -> {
			EmployeeEntity employeeEntity = new EmployeeEntity();
			BeanUtils.copyProperties(emp, employeeEntity);
			employeeEntities.add(employeeEntity);
		});
		employeeRepository.saveAll(employeeEntities);
		responseStatus.setStatusCode(String.valueOf(HttpStatus.CREATED.value()));
		responseStatus.setMessage("Successfully employees are added");

		return responseStatus;
	}

	@Override
	public ResponseStatus updateEmployee(Employee employee) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<EmployeeEntity> optional = employeeRepository.findById(employee.getId());
		if (optional.isPresent()) {
			Optional<EmployeeEntity> duplicateOptional = employeeRepository.findByFirstName(employee.getFirstName());
			if (duplicateOptional.isPresent()) {
				EmployeeEntity duplicateEntity = duplicateOptional.get();
				if (duplicateEntity.getId() != employee.getId()) {
					throw new DuplicateEmployeeException(
							"Employee already exist with name: " + employee.getFirstName());
				}
			}

			EmployeeEntity employeeEntity = optional.get();
			BeanUtils.copyProperties(employee, employeeEntity);
			employeeRepository.save(employeeEntity);

			responseStatus.setStatusCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setMessage("Employee updated successfully");
		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + employee.getId());
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus deleteEmployee(int id) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		if (optional.isPresent()) {
			employeeRepository.deleteById(id);
			responseStatus.setStatusCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setMessage("Employee deleted successfully");
		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + id);
		}
		return responseStatus;
	}
}

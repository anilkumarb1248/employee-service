package com.app.bak.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.bak.entity.AddressEntity;
import com.app.bak.entity.EmployeeEntity;
import com.app.bak.exceptions.DuplicateEmployeeException;
import com.app.bak.exceptions.EmployeeNotFoundException;
import com.app.bak.model.Address;
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
		entitiesList.stream().forEach(employeeEntity -> {
			employeesList.add(convertToBean(employeeEntity));
		});
		return employeesList;
	}

	@Override
	public Employee getEmployee(int employeeId) {

		Optional<EmployeeEntity> optional = employeeRepository.findById(employeeId);
		if (!optional.isPresent()) {
			throw new EmployeeNotFoundException("No employee found with id:" + employeeId);
		}

		EmployeeEntity employeeEntity = optional.get();
		return convertToBean(employeeEntity);
	}

	@Override
	public ResponseStatus addEmployee(Employee employee) {
		
		if (!isDuplicateEmployee(true, employee)) {
			EmployeeEntity emp = employeeRepository.save(convertToEntity(employee));

			if (null != emp) {
				return createResponseStatus(HttpStatus.CREATED, "Employee added successfully");
			} else {
				return createResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY, "Failed to add Employee");
			}
		} else {
			throw new DuplicateEmployeeException("Employee already exist with name: " + employee.getFirstName());
		}

	}

	@Override
	public ResponseStatus addEmployees(List<Employee> employees) {

		List<EmployeeEntity> employeeEntities = new ArrayList<>();

		employees.stream().forEach(employee -> {
			if (!isDuplicateEmployee(true, employee)) {
				employeeEntities.add(convertToEntity(employee));
			}
		});

		if (!employeeEntities.isEmpty()) {
			employeeRepository.saveAll(employeeEntities);
		}

		return createResponseStatus(HttpStatus.CREATED, "Employees added successfully");
	}

	@Override
	public ResponseStatus updateEmployee(Employee employee) {

		if (isEmployeeExist(employee.getEmployeeId())) {
			if (isDuplicateEmployee(false, employee)) {
				throw new DuplicateEmployeeException("Employee already exist with name: " + employee.getFirstName());
			}

			employeeRepository.save(convertToEntity(employee));

			return createResponseStatus(HttpStatus.OK, "Employees updated successfully");

		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + employee.getEmployeeId());
		}
	}

	@Override
	public ResponseStatus deleteEmployee(int employeeId) {

		if (isEmployeeExist(employeeId)) {
			employeeRepository.deleteById(employeeId);

			return createResponseStatus(HttpStatus.OK, "Employees deleted successfully");
		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + employeeId);
		}
	}

	private Employee convertToBean(EmployeeEntity employeeEntity) {
		Employee employee = new Employee();

		BeanUtils.copyProperties(employeeEntity, employee);
		List<AddressEntity> addressEntityList = employeeEntity.getAddressList();

		if (null != addressEntityList && !addressEntityList.isEmpty()) {
			List<Address> addressList = new ArrayList<>();

			addressEntityList.forEach(addressEntity -> {
				Address address = new Address();
				BeanUtils.copyProperties(addressEntity, address);
				addressList.add(address);
			});
			employee.setAddressList(addressList);
		}
		return employee;
	}

	private EmployeeEntity convertToEntity(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();

		BeanUtils.copyProperties(employee, employeeEntity);

		List<Address> addressList = employee.getAddressList();

		if (null != addressList && !addressList.isEmpty()) {
			List<AddressEntity> addressEntityList = new ArrayList<>();
			addressList.forEach(address -> {
				AddressEntity addressEntity = new AddressEntity();
				BeanUtils.copyProperties(address, addressEntity);
				addressEntityList.add(addressEntity);
			});
			employeeEntity.setAddressList(addressEntityList);
		}
		return employeeEntity;
	}

	private ResponseStatus createResponseStatus(HttpStatus httpStatus, String message) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatusCode(String.valueOf(httpStatus.value()));
		responseStatus.setMessage(message);
		return responseStatus;
	}

	private boolean isDuplicateEmployee(boolean newFlag, Employee employee) {

		Optional<EmployeeEntity> optional = employeeRepository.findByFirstName(employee.getFirstName());
		if (!optional.isPresent()) {
			return false;
		} else {
			EmployeeEntity duplicateEntity = optional.get();
			if (newFlag || duplicateEntity.getEmployeeId() != employee.getEmployeeId()) {
				return true;
			}
		}
		return false;
	}

	private boolean isEmployeeExist(int id) {
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		return optional.isPresent();
	}

}

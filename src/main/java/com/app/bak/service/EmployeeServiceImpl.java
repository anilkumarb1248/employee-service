package com.app.bak.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
	public List<Employee> getEmployeesByPagination(int pageNumber, int pageSize, String sortOrder, String sortingBy) {
		List<Employee> employeesList = new ArrayList<>();

		try {
			Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortingBy);
			Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
			Page<EmployeeEntity> page = employeeRepository.findAll(pageable);
			List<EmployeeEntity> entitiesList = page.getContent();
			entitiesList.stream().forEach(employeeEntity -> {
				employeesList.add(convertToBean(employeeEntity));
			});

		} catch (Exception e) {
			LOGGER.error("Error occured while fetching the employees by pagination");
		}
		return employeesList;
	}

	@Override
	@Cacheable(cacheNames = "employees", key = "#employeeId")
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
	@CacheEvict(cacheNames = "employees", key = "#employeeId")
	public ResponseStatus deleteEmployee(int employeeId) {

		if (isEmployeeExist(employeeId)) {
			employeeRepository.deleteById(employeeId);

			return createResponseStatus(HttpStatus.OK, "Employee deleted successfully");
		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + employeeId);
		}
	}

	@Override
	@CacheEvict(cacheNames = "employees")
	public ResponseStatus deleteAll() {
		try {
//			employeeRepository.deleteAllInBatch(); // Not working due to Address constraints

			List<EmployeeEntity> entitiesList = employeeRepository.findAll();
			employeeRepository.deleteAll(entitiesList);
			return createResponseStatus(HttpStatus.OK, "Employees deleted successfully");
		} catch (Exception e) {
			return createResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete all employees");
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
//		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
//		return optional.isPresent();]
		return employeeRepository.existsById(id);
	}

	@Override
	@Cacheable(cacheNames = "employees", key = "#name")
	public Employee getEmployeeByName(String firstName) {

		Optional<EmployeeEntity> optional = employeeRepository.findByFirstName(firstName);
		if (!optional.isPresent()) {
			throw new EmployeeNotFoundException("No employee found with firstName:" + firstName);
		}

		EmployeeEntity employeeEntity = optional.get();
		return convertToBean(employeeEntity);
	}

	@Override
	public boolean addDummyData() {

		try {
			ObjectMapper mapper = new ObjectMapper();
			Resource resource = new ClassPathResource("employees-data.json");
			InputStream inputStream = resource.getInputStream();
			TypeReference<List<Employee>> typeReference = new TypeReference<List<Employee>>() {
			};

			List<Employee> list = mapper.readValue(inputStream, typeReference);

//			addEmployees(list);

			List<EmployeeEntity> entityList = new ArrayList<>();
			list.forEach(employee -> {
				if (!isDuplicateEmployee(true, employee)) {
					entityList.add(convertToEntity(employee));
				}
			});
			employeeRepository.saveAll(entityList);
			LOGGER.info("Created dummy data successfully");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return false;
	}

	@Override
	public void callStoredProcedure() {
		// Created FIND_LESS_SALARIS_EMPLOYEES procedure in mysql, find in DML.txt
		LOGGER.info("Procedure call Started");
		List<EmployeeEntity> list = employeeRepository.findEmployeesBySalary(30000.00);
		list.forEach(entity->{
			LOGGER.info(entity.toString());
		});
		
//		HashMap<Object, Object> map = employeeRepository.findEmployeesBySalary(30000.00);
//		LOGGER.info(map.size()+"");
		LOGGER.info("Procedure call Ended");

	}

}

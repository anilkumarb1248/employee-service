package com.app.bak.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.app.bak.enums.Gender;
import com.app.bak.enums.MaritalStatus;
import com.app.bak.enums.Role;
import com.app.bak.model.Employee;
import com.app.bak.util.ResponseStatus;

@Component("employeeDataHandler")
public class EmployeeDataHandler {

	private static int autoIncreamentEmpId;
	private static Map<Integer, Employee> EMPLOYEES_DATA_MAP = new HashMap<>();

	static {
		loadData();
	}

	private static void loadData() {
		autoIncreamentEmpId = 100;
		EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId, new Employee(autoIncreamentEmpId++, "Anil", "Kumar", "Bandari", Role.TA, 10000, getDOB("05/02/1990"), Gender.MALE, "123456789", "anil@gmail.com", "YRP", MaritalStatus.MARRIED));
		EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId, new Employee(autoIncreamentEmpId++, "Manasvi", "", "Bandari", Role.LEAD, 20000, getDOB("08/15/1998"), Gender.FEMALE, "123456789", "manu@gmail.com", "YRP", MaritalStatus.MARRIED));
		EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId, new Employee(autoIncreamentEmpId++, "Sahanvika", "Pinkulu", "Bandari", Role.CEO, 30000, getDOB("10/23/2020"), Gender.FEMALE, "123456789", "pinky@gmail.com", "YRP", MaritalStatus.UNMARRIED));
		EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId, new Employee(autoIncreamentEmpId++, "Jasmitha", "Jassy", "Bandari", Role.SE, 40000, getDOB("01/01/2007"), Gender.FEMALE, "123456789", "jasmitha@gmail.com", "YRP", MaritalStatus.UNMARRIED));
		EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId, new Employee(autoIncreamentEmpId++, "Jasvanth", "Jassy", "Bandari", Role.MANAGER, 50000, getDOB("04/24/2010"), Gender.MALE, "123456789", "jasvanth@gmail.com", "YRP", MaritalStatus.UNMARRIED));
	}

	private static Date getDOB(String dob) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
			return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}

	public List<Employee> getEmployeeList() {
		return new ArrayList<Employee>(EMPLOYEES_DATA_MAP.values());
	}

	public Employee getEmployee(int id) {
		return EMPLOYEES_DATA_MAP.get(id);
	}

	public ResponseStatus addEmployee(Employee employee) {
		ResponseStatus status = new ResponseStatus();

		if (!EMPLOYEES_DATA_MAP.containsKey(employee.getId())) {
			employee.setId(autoIncreamentEmpId);
			EMPLOYEES_DATA_MAP.put(autoIncreamentEmpId++, employee);

			status.setMessage("Employee added successfully");
			status.setStatusCode("201");
		} else {
			status.setErrorMessage("Failed to add employee-duplicate entry found");
			status.setStatusCode("409"); // CONFLICT - means duplicate entry
		}

		return status;
	}

	public ResponseStatus updateEmployee(Employee employee) {
		ResponseStatus status = new ResponseStatus();
		if (EMPLOYEES_DATA_MAP.containsKey(employee.getId())) {
			EMPLOYEES_DATA_MAP.put(employee.getId(), employee);
			status.setMessage("Employee updated successfully with id: " + employee.getId());
			status.setStatusCode("200");
		} else {
			status.setErrorMessage("Failed to update employee, No Employee found with id: " + employee.getId());
			status.setStatusCode("204"); // means No-Content
		}
		return status;
	}

	public ResponseStatus deleteEmployee(int id) {
		ResponseStatus status = new ResponseStatus();
		if (EMPLOYEES_DATA_MAP.containsKey(id)) {
			EMPLOYEES_DATA_MAP.remove(id);
			status.setMessage("Employee deleted successfully with id: " + id);
			status.setStatusCode("200");
		} else {
			status.setErrorMessage("Failed to delete employee with id: " + id);
			status.setStatusCode("204"); // means No-Content
		}
		return status;
	}

	public void refreshEmployeeList() {
		loadData();
	}

}

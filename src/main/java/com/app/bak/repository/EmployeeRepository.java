package com.app.bak.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.app.bak.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	Optional<EmployeeEntity>  findByFirstName(String name);
	
	@Procedure(name = "EmployeeEntity.findLessSalariesEmployees")
	List<EmployeeEntity> findEmployeesBySalary(@Param("IN_SALARY") Double salary);
	
//	@Procedure(procedureName = "FIND_LESS_SALARIS_EMPLOYEES")
//	List<EmployeeEntity> getEmployeesBySalary(@Param("IN_SALARY") Double salary);

}


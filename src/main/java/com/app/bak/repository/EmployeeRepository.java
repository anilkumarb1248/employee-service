package com.app.bak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bak.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

	Optional<EmployeeEntity>  findByFirstName(String name);

}

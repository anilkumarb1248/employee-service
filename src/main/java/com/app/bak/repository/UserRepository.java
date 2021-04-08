package com.app.bak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.bak.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	Optional<UserEntity> findByUserId(String userId);

}

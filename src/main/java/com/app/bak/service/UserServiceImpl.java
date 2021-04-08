package com.app.bak.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.bak.entity.UserEntity;
import com.app.bak.exceptions.DuplicateUserException;
import com.app.bak.exceptions.EmployeeNotFoundException;
import com.app.bak.exceptions.UserNotFoundException;
import com.app.bak.model.User;
import com.app.bak.repository.UserRepository;
import com.app.bak.util.ResponseStatus;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUserList() {
		List<UserEntity> entities = userRepository.findAll();

		List<User> users = new ArrayList<>();

		entities.stream().forEach(entity -> {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			users.add(user);
		});
		return users;
	}

	@Override
	public User getUser(int id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			UserEntity entity = optional.get();
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			return user;
		} else {
			throw new UserNotFoundException("User not found with id: " + id);
		}
	}

	@Override
	public User getUserByUserId(String userId) {
		Optional<UserEntity> optional = userRepository.findByUserId(userId);
		if (optional.isPresent()) {
			UserEntity entity = optional.get();
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			return user;
		} else {
			throw new UserNotFoundException("User not found with userId: " + userId);
		}
	}

	@Override
	public ResponseStatus addUser(User user) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<UserEntity> optional = userRepository.findByUserId(user.getUserId());
		if (optional.isPresent()) {
			throw new DuplicateUserException("User already exist with userid: " + user.getUserId());
		}

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity userEntity = userRepository.save(entity);

		if (null != userEntity) {
			responseStatus.setStatusCode(String.valueOf(HttpStatus.CREATED.value()));
			responseStatus.setMessage("User added successfully");
		} else {
			responseStatus.setStatusCode(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value())); // UNPROCESSABLE_ENTITY - 422
			responseStatus.setMessage("Failed to add User");
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus updateUser(User user) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<UserEntity> optional = userRepository.findById(user.getId());

		if (optional.isPresent()) {
			Optional<UserEntity> duplicateOptional = userRepository.findByUserId(user.getUserId());
			if (duplicateOptional.isPresent()) {
				UserEntity duplicateEntity = duplicateOptional.get();
				if (duplicateEntity.getId() != user.getId()) {
					throw new DuplicateUserException("User already exist with userId: " + user.getUserId());
				}
			}

			UserEntity userEntity = optional.get();
			BeanUtils.copyProperties(user, userEntity);
			userRepository.save(userEntity);

			responseStatus.setStatusCode(String.valueOf(HttpStatus.OK.value()));
			responseStatus.setMessage("User updated successfully");
		} else {
			throw new EmployeeNotFoundException("No employee found with id: " + user.getId());
		}
		return responseStatus;
	}

	@Override
	public ResponseStatus deleteUser(int id) {
		ResponseStatus responseStatus = new ResponseStatus();
		Optional<UserEntity> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("No user found with id: " + id);
		}

		userRepository.deleteById(id);
		responseStatus.setStatusCode(String.valueOf(HttpStatus.OK.value()));
		responseStatus.setMessage("User deleted successfully");

		return responseStatus;
	}

}

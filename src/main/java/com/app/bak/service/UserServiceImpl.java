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
			users.add(convertToBean(entity));
		});
		return users;
	}

	@Override
	public User getUser(int id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return convertToBean(optional.get());
		} else {
			throw new UserNotFoundException("User not found with id: " + id);
		}
	}

	@Override
	public User getUserByUserName(String userName) {
		Optional<UserEntity> optional = userRepository.findByUserName(userName);
		if (optional.isPresent()) {
			return convertToBean(optional.get());
		} else {
			throw new UserNotFoundException("User not found with user name: " + userName);
		}
	}

	@Override
	public ResponseStatus addUser(User user) {

		if (!isDuplicateUser(true, user)) {
			UserEntity userEntity = userRepository.save(convertToEntity(user));

			if (null != userEntity) {
				return createResponseStatus(HttpStatus.CREATED, "User added successfully");
			} else {
				return createResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY, "Failed to add User");
			}
		} else {
			throw new DuplicateUserException("User already exist with user name: " + user.getUserName());
		}
	}

	@Override
	public ResponseStatus updateUser(User user) {

		if (isUserExist(user.getId())) {
			if (isDuplicateUser(false, user)) {
				throw new DuplicateUserException("User already exist with user name: " + user.getUserName());
			}

			userRepository.save(convertToEntity(user));
			return createResponseStatus(HttpStatus.OK, "User updated successfully");
		} else {
			throw new UserNotFoundException("No user found with id: " + user.getId());
		}
	}

	@Override
	public ResponseStatus deleteUser(int id) {

		if (isUserExist(id)) {
			userRepository.deleteById(id);
			return createResponseStatus(HttpStatus.OK, "User deleted successfully");
		} else {
			throw new UserNotFoundException("No user found with id: " + id);
		}
	}

	@Override
	public ResponseStatus deleteUserByUsername(String userName) {

		if (isUserExist(userName)) {
			userRepository.deleteByUserName(userName);

			return createResponseStatus(HttpStatus.OK, "User deleted successfully");
		} else {
			throw new UserNotFoundException("No user found with user name: " + userName);
		}
	}

	private User convertToBean(UserEntity userEnitity) {
		User user = new User();
		BeanUtils.copyProperties(userEnitity, user);
		return user;
	}

	private UserEntity convertToEntity(User user) {
		UserEntity userEnitity = new UserEntity();
		BeanUtils.copyProperties(user, userEnitity);
		return userEnitity;
	}

	private ResponseStatus createResponseStatus(HttpStatus httpStatus, String message) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatusCode(String.valueOf(httpStatus.value()));
		responseStatus.setMessage(message);
		return responseStatus;
	}

	private boolean isDuplicateUser(boolean newFlag, User user) {

		Optional<UserEntity> optional = userRepository.findByUserName(user.getUserName());
		if (!optional.isPresent()) {
			return false;
		} else {
			UserEntity duplicateEntity = optional.get();
			if (newFlag || duplicateEntity.getId() != user.getId()) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserExist(int id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		return optional.isPresent();
	}

	private boolean isUserExist(String userName) {
		Optional<UserEntity> optional = userRepository.findByUserName(userName);
		return optional.isPresent();
	}

}

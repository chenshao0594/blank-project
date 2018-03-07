package com.blank.service;

import org.springframework.dao.DataAccessException;

import com.blank.model.User;
import com.blank.model.UserProfile;

public interface UserService {
	public User findUser(String username) throws DataAccessException;

	public UserProfile createUserProfile(User user);

	User saveUser(User user);
}

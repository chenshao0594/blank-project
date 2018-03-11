package com.blank.service;

import org.springframework.dao.DataAccessException;

import com.blank.common.service.AbstractService;
import com.blank.domain.User;
import com.blank.domain.UserProfile;

public interface UserService extends AbstractService<User, Long> {
	public User findUser(String username) throws DataAccessException;

	public UserProfile createUserProfile(User user);

	User saveUser(User user);
}

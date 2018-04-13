package com.blank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.common.service.AbstractServiceImpl;
import com.blank.domain.User;
import com.blank.domain.UserProfile;
import com.blank.repository.AuthorityRepository;
import com.blank.repository.UserRepository;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {
	private final UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	public UserServiceImpl(UserRepository repository) {
		super(repository);
		this.userRepository = repository;

	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(String userName) throws DataAccessException {
		return userRepository.findByUsername(userName);
	}

	@Override
	public UserProfile createUserProfile(User user) {
		return new UserProfile(user);
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		user.addAuthority(authorityRepository.findByAuthority("ROLE_USER"));
		return userRepository.save(user);
	}
}

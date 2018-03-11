package com.blank.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blank.domain.User;
import com.blank.service.UserService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User currentUser = null;
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		currentUser = userService.findUser(username);
		if (currentUser == null) {
			throw new UsernameNotFoundException("user not existed");
		}
		authorities.addAll(currentUser.getAuthorities());
		return new org.springframework.security.core.userdetails.User(username, currentUser.getPassword(), authorities);
	}

}

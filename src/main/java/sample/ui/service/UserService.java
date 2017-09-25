package sample.ui.service;

import org.springframework.dao.DataAccessException;

import sample.ui.model.User;
import sample.ui.model.UserProfile;

public interface UserService {
	public User findUser(String username) throws DataAccessException;

	public UserProfile createUserProfile(User user);

	User saveUser(User user);
}

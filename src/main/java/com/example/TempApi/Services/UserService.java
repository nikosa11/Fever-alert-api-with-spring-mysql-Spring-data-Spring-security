package com.example.TempApi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.TempApi.Model.User;
import com.example.TempApi.Repository.UserRepository;

@Service
public class UserService implements UserServices {

	@Autowired
	private UserRepository userRepository;

	PasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public User createUser(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	@Override
	public void updateUser(long id, String firstname, String lastname, int age, String email, double weight,
			int height) {
		User user = userRepository.findById(id).orElse(null);
		user.setAge(age);
		user.setEmail(email);
		user.setWeight(weight);
		user.setFirstName(firstname);
		user.setHeight(height);
		user.setLastName(lastname);

		userRepository.save(user);
	}

	public User findByLoginPassword(String login, String password) {

		User user = findUserByUserName(login);
		if (user != null) {
			if (encoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean userNameExist(String username) {
		return findUserByUserName(username).getUserName().equals(username);
		
	}

	@Override
	public void updateHealth(String username, String h) {
		User u = findUserByUserName(username);
		u.setStatus(h);
		userRepository.save(u);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);

	}

	@Override
	public long findUserIdByUserNAme(String userName) {
		return findUserByUserName(userName).getUserId();
	}

	@Override
	public Optional<User> findUserByUserId(long userId) {
		return userRepository.findById(userId);
	}

}

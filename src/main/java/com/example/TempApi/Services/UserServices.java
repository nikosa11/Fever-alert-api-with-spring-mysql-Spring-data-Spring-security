package com.example.TempApi.Services;


import java.util.Optional;

import com.example.TempApi.Model.User;

public interface UserServices {
	User createUser(User user);


	void updateUser(long id, String firstname, String lastname, int age, String email, double weight, int height);



	User findByLoginPassword(String login, String password);

	boolean userNameExist(String username);

	void updateHealth(String username, String h);
	
	User findUserByUserName(String userName);
	
	long findUserIdByUserNAme(String UserName);
	
	Optional<User> findUserByUserId(long userId);

}

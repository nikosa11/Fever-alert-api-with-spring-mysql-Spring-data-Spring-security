package com.example.TempApi;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TempApi.Controller.Controller;
import com.example.TempApi.Model.AuthLogin;
import com.example.TempApi.Model.Dates;
import com.example.TempApi.Model.Temperature;
import com.example.TempApi.Model.User;
import com.example.TempApi.Repository.UserRepository;
import com.example.TempApi.Services.TemperatureServices;
import com.example.TempApi.Services.UserServices;

@SpringBootTest
class TempApiApplicationTests {
	@Autowired
	UserRepository repo;
	@Autowired
	UserServices serv;
	
	@Autowired
	TemperatureServices temps;
	@Autowired
	Controller controller;
	
	

	@Test
	void contextLoads() throws Exception {
		
		User u1=new User();
		User u2=new User();
		User u3=new User();
		User u4=new User();
		u1.setUserName("test");u1.setPassword("testtest");u1.setAge(25);u1.setEmail("test@gmail.com");u1.setFirstName("test");u1.setLastName("test");u1.setHeight(189);u1.setWeight(88);
		u2.setUserName("nikos");u2.setPassword("nikoslits");u2.setAge(25);u2.setEmail("nikosl@gmail.com");u2.setFirstName("Nikos");u2.setLastName("Lits");u2.setHeight(189);u2.setWeight(88);
		u3.setUserName("giannis");u3.setPassword("gianniski");u3.setAge(25);u3.setEmail("giannisk@gmail.com");u3.setFirstName("Giannis");u3.setLastName("Ki");u3.setHeight(189);u3.setWeight(88);
		u4.setAge(20);u4.setEmail("testUpdate@gmail.com");u4.setFirstName("testUpdate");u4.setLastName("testUpdate");u4.setHeight(200);u4.setWeight(90);

		AuthLogin authLogin1=new AuthLogin(u1.getUserName(),u1.getPassword());
		AuthLogin authLogin2=new AuthLogin(u2.getUserName(),u2.getPassword());
		AuthLogin authLogin3=new AuthLogin(u3.getUserName(),u3.getPassword());
		
	//if (serv.usernameExist(u1.getUserName()))
		serv.createUser(u1);
	//if (serv.usernameExist(u2.getUserName()))
		serv.createUser(u2);
	//if (serv.usernameExist(u3.getUserName()))
		serv.createUser(u3);
		String token1;
		String token2;
		String token3;
		token1=controller.auth(authLogin1);
		token2=controller.auth(authLogin2);
		token3=controller.auth(authLogin3);
		Temperature t1=new Temperature();t1.setTemperature(36);
		Temperature t2=new Temperature();t2.setTemperature(37);
		Temperature t3=new Temperature();t3.setTemperature(38);
		Temperature t4=new Temperature();t4.setTemperature(39);
		Temperature t5=new Temperature();t5.setTemperature(40);
		Temperature t6=new Temperature();t6.setTemperature(41);
		Temperature t7=new Temperature();t7.setTemperature(42);
		Temperature t8=new Temperature();t8.setTemperature(35);
		controller.temperature(t1, token1, authLogin1.getLogin());
		controller.temperature(t2, token1, authLogin1.getLogin());
		controller.temperature(t3, token2, authLogin2.getLogin());
		controller.temperature(t4, token2, authLogin2.getLogin());
		controller.temperature(t5, token2, authLogin2.getLogin());
		controller.temperature(t6, token3, authLogin3.getLogin());
		controller.temperature(t7, token3, authLogin3.getLogin());
		controller.temperature(t1, token1, authLogin1.getLogin());
		controller.temperature(t8, token1, authLogin1.getLogin());
		Dates dates=new Dates();
		dates.setStartDate("2020-01-20");
		dates.setEndDate("2021-02-20");
		controller.feverSessions(dates, token1, authLogin1.getLogin());
		controller.feverSessions(dates, token2, authLogin2.getLogin());
		controller.feverSessions(dates, token3, authLogin3.getLogin());
		controller.update(u4, token1, authLogin1.getLogin());
		controller.info(token1, authLogin1.getLogin());
		controller.user(token1, authLogin1.getLogin());
		token1=controller.logout();
		token2=controller.logout();
		token3=controller.logout();

		


	}
	

}

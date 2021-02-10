package com.example.TempApi.Controller;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.TempApi.Model.AuthLogin;
import com.example.TempApi.Model.Dates;
import com.example.TempApi.Model.Temperature;
import com.example.TempApi.Model.User;
import com.example.TempApi.Services.TemperatureServices;
import com.example.TempApi.Services.UserServices;
import com.example.TempApi.config.jwt.JwtProvider;

@Validated
@RestController
public class Controller {
	@Autowired
	UserServices userServices;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private TemperatureServices temperatureServices;
	static Log log = LogFactory.getLog(Controller.class.getName());

	@PostMapping("/signin")
	public String auth(@RequestBody AuthLogin authLogin) {
		try {
			User user = userServices.findByLoginPassword(authLogin.getLogin(), authLogin.getPassword());
			String token = jwtProvider.generateToken(user.getUserName());
			log.info("congratulations you have successfully sign in, your token are :" + token);
			return token;
		} catch (Exception e) {
			log.info("{Error:Invalid Username or Password}");

			return "{Error:Invalid Username or Password}";
		}

	}

	@GetMapping("/user/logout")
	public String logout() {
		log.info("you successfully log out !!");
		return "0";
	}

	@GetMapping("/user/check") //
	public User user(String token, String userName) {
		try {
			if (userName.equals(jwtProvider.getLoginFromToken(token))) {
			}
			return userServices.findUserByUserName(userName);

		} catch (Exception e) {
			log.info("you must log in");
			return null;
		}
	}

	@PostMapping("/user/update")
	public User update(@RequestBody User u, String token, String userName) {
		try {
			if (userName.equals(jwtProvider.getLoginFromToken(token))) {

			}
			User user = userServices.findUserByUserName(userName);
			userServices.updateUser(user.getUserId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getEmail(),
					u.getWeight(), u.getHeight());
			return userServices.findUserByUserName(userName);
		} catch (Exception e) {
			log.info("you must log in");
			return null;
		}

	}

	@GetMapping("/user/info")
	public User info(String token, String userName) {
		try {
			if (userName.equals(jwtProvider.getLoginFromToken(token))) {

			}
			return userServices.findUserByUserName(userName);
		} catch (Exception e) {
			log.info("you must log in");
			return null;
		}

	}

	@PostMapping("/user/temperature")
	public Temperature temperature(@RequestBody Temperature t, String token, String userName) throws Exception {
		try {
			if (userName.equals(jwtProvider.getLoginFromToken(token))) {

			}
		} catch (Exception e) {
			log.info("you must log in");

			throw new Exception("you must log in");

		}

		if (t.getTemperature() < 35 || t.getTemperature() > 42) {
			throw new Exception("temperature out of range");
		}
		long userId = userServices.findUserIdByUserNAme(userName);
		temperatureServices.adaptHealthStatus(userId, userName, t);

		return t;

	}

	@PostMapping("/user/dates")
	public ArrayList<Temperature> feverSessions(@RequestBody Dates ds, String token, String userName) throws Exception {

		if (!userName.equals(jwtProvider.getLoginFromToken(token))) {
			log.info("you must log in");
			throw new Exception("you must log in");
		}
		try {
			return temperatureServices.getFeverSessions(ds.StringToDate(ds.getStartDate()),
					ds.StringToDate(ds.getEndDate()), userServices.findUserByUserName(userName).getUserId());
		} catch (Exception e) {
			log.error("enter the dates correctly");
			log.error(" startdate must be before enddate");
			log.error(" date:yyyy-mm-dd  ex:startdate: 2021-01-15 enddate: 2021-01-25");
			return null;
		}

	}

}
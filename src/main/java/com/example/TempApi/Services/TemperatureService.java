package com.example.TempApi.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TempApi.Model.Temperature;
import com.example.TempApi.Repository.TemperatureRepository;

@Service
public class TemperatureService implements TemperatureServices {
	@Autowired
	TemperatureRepository temperatureRepository;
	@Autowired
	UserServices userServices;


	@Override
	public String CreateTemperature(Temperature temperature, long userId) {
		temperature.setUserId(userId);
		temperatureRepository.save(temperature);
		return "Done";
	}

	@Override
	public void trigger(long userId, int flag) {
		ArrayList<Temperature> temperatures = new ArrayList<Temperature>();
		temperatures = findByUserId(userId);

		for (Temperature t : temperatures) {
			if ((t.getFlag() == 1)) {
				if (flag == 1) {
					System.out.println(t.toString());
				} else {// if he is healthy, no more trigger
					t.setFlag(0);
					temperatureRepository.save(t);
				}
			}
		}
	}

	@Override
	public ArrayList<Temperature> getFeverSessions(LocalDate startDate, LocalDate endDate, long userId) {
		boolean exist = false;
		ArrayList<Temperature> dates = new ArrayList<Temperature>();

		ArrayList<Temperature> temperatures = findByUserId(userId);
		for (Temperature t : temperatures) {
			if (t.getDate().isAfter(startDate) && t.getDate().isBefore(endDate)) {
				dates.add(t);
				exist = true;
			}

		}

		if (!exist)
			return null;
		return dates;

	}

	@Override
	public ArrayList<Temperature> findByUserId(Long userId) {
		return temperatureRepository.findByUserId(userId);
	}

	@Override
	public void adaptHealthStatus(long userId, String userName, Temperature t) {
		if (t.getTemperature() >= 38 && userServices.findUserByUserName(userName).getStatus().equals("healthy")) {// ongoing_fever
			// and previous
			// status
			// healthy
			t.setFlag(1);
			t.setHealth("ongoing_fever");
			CreateTemperature(t, userId);
			userServices.updateHealth(userName, "ongoing_fever");
			trigger(userId, t.getFlag());//if fever begin start trigger
		} else if ((t.getTemperature() >= 37)
				&& userServices.findUserByUserName(userName).getStatus().equals("ongoing_fever")) {// ongoing_fever
																									// and
																									// previous
																									// status
																									// ongoing_fever
			t.setFlag(1);
			t.setHealth("ongoing_fever");
			CreateTemperature(t, userId);
			trigger(userId, t.getFlag());// trigger keep going

		} else if ((t.getTemperature() < 37)
				&& userServices.findUserByUserName(userName).getStatus().equals("ongoing_fever")) {// healthy
																									// and
																									// previous
																									// ongoing_fever
			t.setFlag(0);
			t.setHealth("healthy");

			CreateTemperature(t, userId);
			userServices.updateHealth(userName, "healthy");
			trigger(userId, 0);// stop the trigger

		} else if (t.getTemperature() < 38 && userServices.findUserByUserName(userName).getStatus().equals("healthy")) {
			t.setFlag(0);
			t.setHealth("healthy");
			CreateTemperature(t, userId);
			userServices.updateHealth(userName, "healthy");

		}

	}

}

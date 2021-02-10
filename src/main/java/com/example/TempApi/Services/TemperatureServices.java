package com.example.TempApi.Services;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.TempApi.Model.Temperature;

public interface TemperatureServices {
	public String CreateTemperature(Temperature temp, long userId);

	public void trigger(long userid, int counter);

	public ArrayList<Temperature> getFeverSessions(LocalDate startdate, LocalDate enddate, long userId);
	
	ArrayList<Temperature> findByUserId(Long userId);
	
	void adaptHealthStatus(long userId,String userName, Temperature t);


}

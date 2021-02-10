package com.example.TempApi.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Temperature {
	@Min(35)
	@Max(42)
	private double temperature;
	LocalDate date = LocalDate.now();
	@Id
	@GeneratedValue
	private long id;
	private long userId;

	private int flag = 0;

	private String health = "healthy";

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Temperature [temperature=" + temperature + "°C" + ", date=" + date + ", health=" + health + "]";
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int counter) {
		this.flag = counter;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}

package com.example.TempApi.Model;

import java.time.LocalDate;

public class Dates {
	private String startDate;
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public LocalDate StringToDate(String date) {
		return LocalDate.parse(date);

	}

}

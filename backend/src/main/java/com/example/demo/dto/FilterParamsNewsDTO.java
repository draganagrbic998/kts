package com.example.demo.dto;

import java.util.Date;

public class FilterParamsNewsDTO {

	private Date startDate;
	private Date endDate;

	public FilterParamsNewsDTO() {
		super();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
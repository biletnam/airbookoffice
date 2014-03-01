package com.vitaliyr.app.service;

import java.util.Date;

public class Report {
	private Date fdate;
	private String desCity;
	private long ticketCount;
	private double flightSum;

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public String getDesCity() {
		return desCity;
	}

	public void setDesCity(String desCity) {
		this.desCity = desCity;
	}

	public double getFlightSum() {
		return flightSum;
	}

	public void setFlightSum(double flightSum) {
		this.flightSum = flightSum;
	}

	public long getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(long ticketCount) {
		this.ticketCount = ticketCount;
	}

	/*
	 * For Date report
	 */

	public Report(Date fdate, long ticketCount, double flightSum) {
		super();
		this.fdate = fdate;
		this.ticketCount = ticketCount;
		this.flightSum = flightSum;
	}

	/*
	 * For Destination report
	 */

	public Report(String desCity, long ticketCount, double flightSum) {
		super();
		this.desCity = desCity;
		this.ticketCount = ticketCount;
		this.flightSum = flightSum;
	}

	public Report() {
		super();
	}

}
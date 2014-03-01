package com.vitaliyr.app.web;

import java.io.Serializable;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.service.AdministratorService;

@Named("admin")
@Scope("session")
public class AdministratorBean implements Serializable {

	@Inject
	AdministratorService administratorService;

	private static final long serialVersionUID = 1L;

	private List<Flights> flightsList;
	private boolean actual;

	/*
	 * Filter fields
	 */
	private java.util.Date startDate = new java.util.Date();
	private java.util.Date endDate;


	public void showFlights(ActionEvent e) {
		if (!actual) {
			flightsList = administratorService.getFlights(startDate, endDate);
		} else {
			flightsList = administratorService.getActualFlights(startDate, endDate);
		}
	}

	public void convertToFree(ActionEvent e) {
		administratorService.convertBooked();
	}

	/*
	 * DataTable operations
	 */
	public String editFlight(Flights f) {
		f.setEditable(true);
		return null;
	}

	public String saveFlightItem(Flights f) {
		administratorService.updateFlight(f);
		f.setEditable(false);
		return null;
	}
	
	public String cancelEditingFlightItem(Flights f) {
		f.setEditable(false);
		return null;
	}


	/*
	 * Getters & Setters
	 */

	public List<Flights> getFlightsList() {
		return flightsList;
	}

	public void setFlightsList(List<Flights> flightsList) {
		this.flightsList = flightsList;
	}


	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}


	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}

	public AdministratorBean() {
	}

}

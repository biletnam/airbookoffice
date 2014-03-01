package com.vitaliyr.app.web;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.service.CustomerService;

@Named("home")
@Scope("session")
public class CustomerBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	CustomerService customerService;

	private java.util.Date startDate = new java.util.Date();
	private java.util.Date endDate;
	private String desCity;
	private List<Flights> foundList;
	
	public void findFlights(ActionEvent e) {
		foundList = customerService.getFlights(startDate, endDate, desCity);
	}
	
	
	/*
	 * List of cities
	 */
	public List<String> complete(String query) {  
		List<String> list = customerService.readCitiesList();
		return list;  
	    }
	
	
	/*
	 * Getters & Setters
	 */
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

	public String getDesCity() {
		return desCity;
	}

	public void setDesCity(String desCity) {
		this.desCity = desCity;
	}

	public List<Flights> getFoundList() {
		return foundList;
	}

	public void setFoundList(List<Flights> foundList) {
		this.foundList = foundList;
	}
	
	public CustomerBean() {
	}

}

package com.vitaliyr.app.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.service.AdministratorService;

@Named("adminAddFlight")
@Scope("request")
public class AdministratorAddFlightBean {

	@Inject
	AdministratorService administratorService;

	private String fcode;
	private String desCity;
	private String desPort;
	private java.util.Date fdate;
	private int avSeats;
	private double ticketPrice;

	public String addNewFlight() {

		administratorService.addFlight(fcode, desCity, desPort, fdate, avSeats,
				ticketPrice);

		return null;
	}

	public void addInfo(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Flight added",
						"Flight added"));
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getDesCity() {
		return desCity;
	}

	public void setDesCity(String desCity) {
		this.desCity = desCity;
	}

	public String getDesPort() {
		return desPort;
	}

	public void setDesPort(String desPort) {
		this.desPort = desPort;
	}

	public java.util.Date getFdate() {
		return fdate;
	}

	public void setFdate(java.util.Date fdate) {
		this.fdate = fdate;
	}

	public int getAvSeats() {
		return avSeats;
	}

	public void setAvSeats(int avSeats) {
		this.avSeats = avSeats;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public AdministratorAddFlightBean() {
	}

}

package com.vitaliyr.app.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Flights {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fcode;
	private String desCity;
	private String desPort;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fdate;
	private int avSeats;
	private double ticketPrice;
	private short hide;

	@Transient
	private boolean selected;
	@Transient
	private boolean editable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
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

	public short getHide() {
		return hide;
	}

	public void setHide(short hide) {
		this.hide = hide;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Flights() {
	}

	public Flights(String fcode, String desCity, String desPort, Date fdate,
			int avSeats, double ticketPrice, short hide) {
		super();
		this.fcode = fcode;
		this.desCity = desCity;
		this.desPort = desPort;
		this.fdate = fdate;
		this.avSeats = avSeats;
		this.ticketPrice = ticketPrice;
		this.hide = hide;
	}

	public Flights(String fcode, String desCity, String desPort, Date fdate,
			int avSeats, double ticketPrice) {
		super();
		this.fcode = fcode;
		this.desCity = desCity;
		this.desPort = desPort;
		this.fdate = fdate;
		this.avSeats = avSeats;
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Flights [id=" + id + ", fcode=" + fcode + ", desCity="
				+ desCity + ", desPort=" + desPort + ", fdate=" + fdate
				+ ", avSeats=" + avSeats + ", ticketPrice=" + ticketPrice + "]";
	}

}

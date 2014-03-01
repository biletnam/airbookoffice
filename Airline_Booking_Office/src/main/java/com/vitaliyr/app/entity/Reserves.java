package com.vitaliyr.app.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Reserves {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int flightId;
	@ManyToOne
	@JoinColumn(name = "orderid")
	private Orders order;
	private String fcode;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fdate;
	private String desCity;
	private String desPort;
	private int ticketCount;
	private double ticketPrice;

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

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

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

	public String getDesPort() {
		return desPort;
	}

	public void setDesPort(String desPort) {
		this.desPort = desPort;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
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

	public Reserves() {
	}

	public Reserves(int flightId, String fcode, Date fdate,
			String desCity, String desPort, int ticketCount, double ticketPrice) {
		super();
		this.flightId = flightId;
		this.fcode = fcode;
		this.fdate = fdate;
		this.desCity = desCity;
		this.desPort = desPort;
		this.ticketCount = ticketCount;
		this.ticketPrice = ticketPrice;
	}

	@Override
	public String toString() {
		return "Reserves [id=" + id + ", flightId=" + flightId + ", fcode="
				+ fcode + ", fdate=" + fdate + ", desCity=" + desCity
				+ ", desPort=" + desPort + ", ticketCount=" + ticketCount
				+ ", ticketPrice=" + ticketPrice + "]";
	}

}

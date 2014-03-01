package com.vitaliyr.app.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private java.sql.Timestamp orDate;
	private String customerMail;
	private String customerName;
	private double total;
	private short status;
	@OneToMany(mappedBy = "order", orphanRemoval = true)
	private List<Reserves> reserves;

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

	public java.sql.Timestamp getOrDate() {
		return orDate;
	}

	public void setOrDate(java.sql.Timestamp orDate) {
		this.orDate = orDate;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public List<Reserves> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserves> reserves) {
		this.reserves = reserves;
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

	public Orders() {
		super();
	}

	public Orders(Timestamp orDate, String customerMail, String customerName,
			double total, short status) {
		super();
		this.orDate = orDate;
		this.customerMail = customerMail;
		this.customerName = customerName;
		this.total = total;
		this.status = status;
	}

	public Orders(Timestamp orDate, String customerMail, String customerName,
			double total, List<Reserves> reserves) {
		super();
		this.orDate = orDate;
		this.customerMail = customerMail;
		this.customerName = customerName;
		this.total = total;
		this.reserves = reserves;
	}
	

	public Orders(Timestamp orDate, String customerMail, String customerName,
			double total, short status, List<Reserves> reserves) {
		super();
		this.orDate = orDate;
		this.customerMail = customerMail;
		this.customerName = customerName;
		this.total = total;
		this.status = status;
		this.reserves = reserves;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orDate=" + orDate + ", customerMail="
				+ customerMail + ", customerName=" + customerName + ", total="
				+ total + ", status=" + status + "]";
	}

}

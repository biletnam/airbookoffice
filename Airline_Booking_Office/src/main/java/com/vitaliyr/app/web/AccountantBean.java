package com.vitaliyr.app.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.entity.Orders;
import com.vitaliyr.app.service.AccountantService;

@Named("account")
@Scope("session")
public class AccountantBean implements Serializable {

	@Inject
	AccountantService accountantService;

	private static final long serialVersionUID = 1L;

	public void showOrders(ActionEvent e) {
		if (!sold) {
			orList = accountantService.getAllBookedOrders(startDate, endDate);
		} else {
			orList = accountantService.getAllSoldOrders(startDate, endDate);
		}
	}

	private List<Orders> orList;
	private java.util.Date startDate = new java.util.Date();
	private java.util.Date endDate;
	boolean sold;

	public String setOrderSold() {
		for (Orders o : orList) {
			if (o.isSelected()) {
				accountantService.setOrderSold(o.getId());
				o.setSelected(false);
			}
		}
		orList = accountantService.getAllBookedOrders(startDate, endDate);
		return null;
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

	public List<Orders> getOrList() {
		return orList;
	}

	public void setOrList(List<Orders> orList) {
		this.orList = orList;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public AccountantBean() {
	}

}

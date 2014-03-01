package com.vitaliyr.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.AccountantDAO;
import com.vitaliyr.app.entity.Orders;


@Named
public class AccountantService {
	@Inject
	AccountantDAO accountantDAO;
	
	
	
	@Transactional
	public List<Orders> getAllBookedOrders(Date startDate, Date endDate) {
		return accountantDAO.getAllBooked(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()));
	}
	
	public List<Orders> getAllSoldOrders(Date startDate, Date endDate) {
		return accountantDAO.getAllSold(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()));
	}
	
	@Transactional
	public void setOrderSold(int id) {
		accountantDAO.setSold(id);
	}
	
}

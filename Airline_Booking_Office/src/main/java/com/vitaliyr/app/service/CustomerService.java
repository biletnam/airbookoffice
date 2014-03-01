package com.vitaliyr.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.CustomerDAO;
import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.entity.Reserves;

@Named
public class CustomerService {
	@Inject
	private CustomerDAO customerDAO;

	@Transactional
	public List<Flights> getFlights(Date startDate, Date endDate, String desCity) {

		return customerDAO.getAvailableFlights(
				new Timestamp(startDate.getTime()),
				new Timestamp(endDate.getTime()), desCity);

	}
	
	/*
	 * Current day flights for WebService
	 */
	@Transactional
	public List<Flights> getCurrentDayFlights() {
		return customerDAO.getCurrentDayFlights();
	}
	

	@Transactional
	public String addNewOrder(List<Reserves> cart, double orderSum,
			String customerMail, String customerName) {

		for (Reserves r : cart) {
			if (!checkAvailableSeats(r.getFlightId(), r.getTicketCount())) {
				return null;
			}
		}
		int id = customerDAO.addOrder(cart, orderSum, customerMail,
				customerName);
		return String.valueOf(id);
	}

	@Transactional
	public boolean checkAvailableSeats(int id, int ticketsCount) {
		return customerDAO.checkAvailableSeats(id, ticketsCount);
	}
	
	
	/*
	 * List of cities for autoComplete
	 */
	public List<String> readCitiesList() {
		List<String> list = customerDAO.getCities();
		return list;

	}
}

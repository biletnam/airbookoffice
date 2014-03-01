package com.vitaliyr.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.AdministratorDAO;
import com.vitaliyr.app.entity.Flights;


@Named
public class AdministratorService {
	@Inject
	private AdministratorDAO administratorDAO;
	
	
	@Transactional
	public List<Flights> getFlights(Date startDate, Date endDate) {
		
		return administratorDAO.getAllFlights(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()));
		
	}
	
	@Transactional
	public List<Flights> getActualFlights(Date startDate, Date endDate) {
		
		return administratorDAO.getActualFlights(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()));
		
	}
	
	
	@Transactional
	public void addFlight(String fcode, String desCity, String desPort, Date fdate, int avSeats, double ticketPrice) {
		
		Flights flight = new Flights(fcode, desCity, desPort, fdate, avSeats, ticketPrice);
		administratorDAO.saveFlight(flight);
		
	}
	
	@Transactional
	public void updateFlight(Flights upd) {
		
		administratorDAO.updateFlight(upd);
		
	}
	
	/*
	 * Hourly task
	 */
	@Scheduled(cron="0 0 * * * *")
	@Async
	@Transactional
	public void convertBooked() {
		administratorDAO.convertBookedToFree();
	}
	
	
	
}

	

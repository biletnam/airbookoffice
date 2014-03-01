package com.vitaliyr.app.dao;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.entity.Orders;
import com.vitaliyr.app.entity.Reserves;

@Repository
public class AdministratorDAO {

	@PersistenceContext
	private EntityManager em;


	public int saveFlight(Flights ent) {
		em.persist(ent);
		em.flush();
		return ent.getId();
	}

	public void updateFlight(Flights ent) {
		em.merge(ent);
	}



	private void deleteOrder(int id) {

		Orders toDelete = em.find(Orders.class, id);
		
		List<Reserves> tb = toDelete.getReserves();
		
		Flights toUpd = null;
		
		int flightId = 0;
		
		int tback = 0;

		for (Reserves r : tb) {
			flightId = r.getFlightId();
			tback = r.getTicketCount();
			toUpd = em.find(Flights.class, flightId);
			toUpd.setAvSeats(toUpd.getAvSeats() + tback);
		}

		em.remove(toDelete);
	}


	public List<Flights> getAllFlights(Timestamp startDate, Timestamp endDate) {
		List<Flights> result = null;

		GregorianCalendar g1 = new GregorianCalendar();
		g1.setTime(startDate);
		g1.set(GregorianCalendar.HOUR_OF_DAY, 00);
		g1.set(GregorianCalendar.MINUTE, 00);
		g1.set(GregorianCalendar.SECOND, 00);

		GregorianCalendar g2 = new GregorianCalendar();
		g2.setTime(endDate);
		g2.set(GregorianCalendar.HOUR_OF_DAY, 23);
		g2.set(GregorianCalendar.MINUTE, 59);
		g2.set(GregorianCalendar.SECOND, 59);

		java.sql.Timestamp st = new java.sql.Timestamp(g1.getTimeInMillis());
		java.sql.Timestamp nd = new java.sql.Timestamp(g2.getTimeInMillis());

		TypedQuery<Flights> query = em.createQuery("SELECT f FROM Flights f WHERE f.fdate BETWEEN :startDate AND :endDate ORDER BY f.fdate", Flights.class);
		query.setParameter("startDate", st);
		query.setParameter("endDate", nd);
		
		result = query.getResultList();

		return result;
	}
	
	public List<Flights> getActualFlights(Timestamp startDate, Timestamp endDate) {
		List<Flights> result = null;

		GregorianCalendar g1 = new GregorianCalendar();
		g1.set(GregorianCalendar.HOUR_OF_DAY, 00);
		g1.set(GregorianCalendar.MINUTE, 00);
		g1.set(GregorianCalendar.SECOND, 00);

		GregorianCalendar g2 = new GregorianCalendar();
		g2.setTime(endDate);
		g2.set(GregorianCalendar.HOUR_OF_DAY, 23);
		g2.set(GregorianCalendar.MINUTE, 59);
		g2.set(GregorianCalendar.SECOND, 59);

		java.sql.Timestamp st = new java.sql.Timestamp(g1.getTimeInMillis());
		java.sql.Timestamp nd = new java.sql.Timestamp(g2.getTimeInMillis());

		TypedQuery<Flights> query = em.createQuery("SELECT f FROM Flights f WHERE f.fdate BETWEEN :startDate AND :endDate AND f.hide = 0 AND f.avSeats > 0 ORDER BY f.fdate", Flights.class);
		query.setParameter("startDate", st);
		query.setParameter("endDate", nd);
		
		result = query.getResultList();

		return result;
	}
	

	
	
	public void convertBookedToFree() {

		List<Orders> toFree = null;
		GregorianCalendar current = new GregorianCalendar();

		toFree = em.createQuery("SELECT o FROM Orders o WHERE o.status = 0",
				Orders.class).getResultList();


		for (Orders r : toFree) {
			GregorianCalendar od = new GregorianCalendar();
			od.setTime(r.getOrDate());
			od.add(GregorianCalendar.HOUR_OF_DAY, 72);
			
			if (od.before(current)) {
				deleteOrder(r.getId());
			}
		}
	}
	
	
	public AdministratorDAO() {
	}

}

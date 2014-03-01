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
public class CustomerDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Flights> getAvailableFlights(Timestamp startDate,
			Timestamp endDate, String desCity) {

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

		Timestamp st = new Timestamp(g1.getTimeInMillis());
		Timestamp nd = new Timestamp(g2.getTimeInMillis());

		List<Flights> result = null;

		TypedQuery<Flights> show = em
				.createQuery(
						"SELECT f FROM Flights f WHERE f.fdate BETWEEN :startDate AND :endDate AND f.desCity = :desCity AND f.hide = 0 AND f.avSeats > 0 ORDER BY f.fdate",
						Flights.class);

		show.setParameter("startDate", st);
		show.setParameter("endDate", nd);
		show.setParameter("desCity", desCity);
		result = show.getResultList();

		return result;
	}
	
	/*
	 * Current day flights for WebService
	 */
	public List<Flights> getCurrentDayFlights() {
		List<Flights> result = null;

		GregorianCalendar g1 = new GregorianCalendar();
		g1.set(GregorianCalendar.HOUR_OF_DAY, 00);
		g1.set(GregorianCalendar.MINUTE, 00);
		g1.set(GregorianCalendar.SECOND, 00);

		GregorianCalendar g2 = new GregorianCalendar();
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
	

	/*
	 * Create new order
	 */
	public int addOrder(List<Reserves> reserves, double orderSum,
			String customerMail, String customerName) {

		for (Reserves r : reserves) {
			Flights upd = em.find(Flights.class, r.getFlightId());
			upd.setAvSeats(upd.getAvSeats() - r.getTicketCount());
		}

		Orders newOrder = new Orders(new Timestamp(
				new GregorianCalendar().getTimeInMillis()), customerMail,
				customerName, orderSum, reserves);
		em.persist(newOrder);
		em.flush();

		for (Reserves r : reserves) {
			r.setOrder(newOrder);
			em.persist(r);
		}

		return newOrder.getId();

	}

	/*
	 * Seats check
	 */
	public boolean checkAvailableSeats(int id, int ticketsCount) {
		Flights flight = em.find(Flights.class, id);
		if (flight.getAvSeats() >= ticketsCount) {
			return true;
		}
		return false;
	}

	/*
	 * Get cities list for autoComplete
	 */
	public List<String> getCities() {

		

		TypedQuery<String> query = em.createQuery(
				"SELECT DISTINCT f.desCity FROM Flights f WHERE f.hide = 0 AND f.avSeats > 0 ORDER BY f.desCity", String.class);

		List<String> list = query.getResultList();
		return list;
	}

	public CustomerDAO() {
	}

}

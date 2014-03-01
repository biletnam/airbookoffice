package com.vitaliyr.app.dao;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.vitaliyr.app.entity.Orders;

@Repository
public class AccountantDAO {

	private static final short STATUS_SOLD = 1;

	@PersistenceContext
	private EntityManager em;

	public void setSold(int id) {

		Orders upd = em.find(Orders.class, id);
		if (upd == null) {
			return;
		}
		upd.setStatus(STATUS_SOLD);

	}

	

	public List<Orders> getAllBooked(Timestamp startDate, Timestamp endDate) {

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

		List<Orders> result = em
				.createQuery(
						"SELECT o FROM Orders o WHERE o.orDate BETWEEN :startDate AND :endDate AND o.status = 0 ORDER BY o.orDate",
						Orders.class).setParameter("startDate", st)
				.setParameter("endDate", nd).getResultList();

		return result;
	}
	
	public List<Orders> getAllSold(Timestamp startDate, Timestamp endDate) {

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

		List<Orders> result = em
				.createQuery(
						"SELECT o FROM Orders o WHERE o.orDate BETWEEN :startDate AND :endDate AND o.status = 1 ORDER BY o.orDate",
						Orders.class).setParameter("startDate", st)
				.setParameter("endDate", nd).getResultList();

		return result;
	}



	public AccountantDAO() {
	}
	
	
	
}

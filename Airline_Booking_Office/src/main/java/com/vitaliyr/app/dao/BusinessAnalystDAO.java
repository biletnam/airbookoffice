package com.vitaliyr.app.dao;

import java.sql.Timestamp;
import java.util.GregorianCalendar;



import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.vitaliyr.app.service.Report;



@Repository
public class BusinessAnalystDAO {

	@PersistenceContext
	private EntityManager em;
	
	public double getTotalSum(Timestamp startDate, Timestamp endDate) {
		
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
		
		TypedQuery<Double> query = em.createQuery(
				"SELECT SUM(r.ticketCount * r.ticketPrice) "
				+ "FROM Reserves r "
				+ "WHERE r.fdate BETWEEN :startDate AND :endDate "
				+ "AND r.order = (SELECT o FROM Orders o WHERE o = r.order AND o.status = 1)", Double.class);
		
		query.setParameter("startDate", st);
		query.setParameter("endDate", nd);
		
		double result = 0;
		
		try {
			result = query.getSingleResult().doubleValue();
			}
			catch (java.lang.NullPointerException e) {
				result = 0;
			}

		return result;
	}
	
	public List<Report> getDateReport(Timestamp startDate, Timestamp endDate) {
		
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
		
		TypedQuery<Report> query = em.createQuery(
				"SELECT new com.vitaliyr.app.service.Report(r.fdate, SUM(r.ticketCount), SUM((r.ticketCount*r.ticketPrice))) "
				+ "FROM Reserves r "
				+ "WHERE r.fdate BETWEEN :startDate AND :endDate "
				+ "AND r.order = (SELECT o FROM Orders o WHERE o = r.order AND o.status = 1) GROUP BY r.fdate", Report.class);
		
		query.setParameter("startDate", st);
		query.setParameter("endDate", nd);
		
		List<Report> result = query.getResultList();
		
		return result;
	}
	
public List<Report> getDestinationReport(Timestamp startDate, Timestamp endDate) {
		
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
		
		TypedQuery<Report> query = em.createQuery(
				"SELECT new com.vitaliyr.app.service.Report(r.desCity, SUM(r.ticketCount), SUM((r.ticketCount*r.ticketPrice))) "
				+ "FROM Reserves r "
				+ "WHERE r.fdate BETWEEN :startDate AND :endDate "
				+ "AND r.order = (SELECT o FROM Orders o WHERE o = r.order AND o.status = 1) GROUP BY r.desCity", Report.class);
		
		query.setParameter("startDate", st);
		query.setParameter("endDate", nd);
		
		List<Report> result = query.getResultList();
		
		return result;
	}
	
	

	public BusinessAnalystDAO() {
	}
}

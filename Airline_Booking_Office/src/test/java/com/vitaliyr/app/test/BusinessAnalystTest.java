package com.vitaliyr.app.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.BusinessAnalystDAO;
import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.entity.Orders;
import com.vitaliyr.app.entity.Reserves;
import com.vitaliyr.app.service.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BusinessAnalystTest {

	@PersistenceContext
	private EntityManager em;

	@Inject
	BusinessAnalystDAO businessAnalystDAO;

	@Test
	public void getTotalSumTest() {
		Flights ent = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		Flights ent2 = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		em.persist(ent);
		em.persist(ent2);
		em.flush();
		int id1 = ent.getId();
		int id2 = ent.getId();
		Reserves r1 = new Reserves(id1, ent.getFcode(), ent.getFdate(),
				ent.getDesCity(), ent.getDesPort(), 1, ent.getTicketPrice());
		Reserves r2 = new Reserves(id2, ent2.getFcode(), ent2.getFdate(),
				ent2.getDesCity(), ent2.getDesPort(), 1, ent2.getTicketPrice());
		List<Reserves> cart = new ArrayList<Reserves>();
		cart.add(r1);
		cart.add(r2);
		Orders order = new Orders(new Timestamp(new Date().getTime()),
				"test@test.com", "Tester", 398.00, (short) 1, cart);
		em.persist(order);
		em.flush();
		r1.setOrder(order);
		r2.setOrder(order);
		em.persist(r1);
		em.persist(r2);

		double totalSum = businessAnalystDAO.getTotalSum(new Timestamp(
				new Date().getTime()), new Timestamp(new Date().getTime()));

		assertEquals(398.00, totalSum, 0.05);

	}

	@Test
	public void getDateReportTest() {
		Flights ent = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		Flights ent2 = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		em.persist(ent);
		em.persist(ent2);
		em.flush();
		int id1 = ent.getId();
		int id2 = ent.getId();
		Reserves r1 = new Reserves(id1, ent.getFcode(), ent.getFdate(),
				ent.getDesCity(), ent.getDesPort(), 1, ent.getTicketPrice());
		Reserves r2 = new Reserves(id2, ent2.getFcode(), ent2.getFdate(),
				ent2.getDesCity(), ent2.getDesPort(), 1, ent2.getTicketPrice());
		List<Reserves> cart = new ArrayList<Reserves>();
		cart.add(r1);
		cart.add(r2);
		Orders order = new Orders(new Timestamp(new Date().getTime()),
				"test@test.com", "Tester", 398.00, (short) 1, cart);
		em.persist(order);
		em.flush();
		r1.setOrder(order);
		r2.setOrder(order);
		em.persist(r1);
		em.persist(r2);

		List<Report> list = businessAnalystDAO.getDateReport(new Timestamp(
				new Date().getTime()), new Timestamp(new Date().getTime()));

		assertEquals(1, list.size());

	}

	@Test
	public void getDestinationReportTest() {
		Flights ent = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		Flights ent2 = new Flights("UPD1", "Moskow", "Domodedovo", new Date(),
				250, 199);
		em.persist(ent);
		em.persist(ent2);
		em.flush();
		int id1 = ent.getId();
		int id2 = ent.getId();
		Reserves r1 = new Reserves(id1, ent.getFcode(), ent.getFdate(),
				ent.getDesCity(), ent.getDesPort(), 1, ent.getTicketPrice());
		Reserves r2 = new Reserves(id2, ent2.getFcode(), ent2.getFdate(),
				ent2.getDesCity(), ent2.getDesPort(), 1, ent2.getTicketPrice());
		List<Reserves> cart = new ArrayList<Reserves>();
		cart.add(r1);
		cart.add(r2);
		Orders order = new Orders(new Timestamp(new Date().getTime()),
				"test@test.com", "Tester", 398.00, (short) 1, cart);
		em.persist(order);
		em.flush();
		r1.setOrder(order);
		r2.setOrder(order);
		em.persist(r1);
		em.persist(r2);

		List<Report> list = businessAnalystDAO.getDestinationReport(new Timestamp(
				new Date().getTime()), new Timestamp(new Date().getTime()));

		assertEquals("Moskow", list.get(0).getDesCity());

	}

}

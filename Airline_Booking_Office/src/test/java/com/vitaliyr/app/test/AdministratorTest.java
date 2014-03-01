package com.vitaliyr.app.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
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

import com.vitaliyr.app.dao.AdministratorDAO;
import com.vitaliyr.app.entity.Flights;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class AdministratorTest {
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private AdministratorDAO administratorDAO;

	@Test
	public void saveTest() {
		
		Flights ent = new Flights("DMD", "Moskow", "Domodedovo", new Date(), 250, 199);
		int id = administratorDAO.saveFlight(ent);
		Flights ent2 = em.find(Flights.class, id);
		assertEquals("DMD", ent2.getFcode());
	}
	
	@Test
	public void updateTest() {
		
		Flights ent = new Flights("UPD1", "Moskow", "Domodedovo", new Date(), 250, 199);
		int id = administratorDAO.saveFlight(ent);
		ent.setDesCity("London");
		administratorDAO.updateFlight(ent);
		Flights ent2 = em.find(Flights.class, id);
		assertEquals("London", ent2.getDesCity());
	}
	
	@Test
	public void getAllTest() {
		
		Flights ent = new Flights("UPD1", "Moskow", "Domodedovo", new Date(), 250, 199);
		Flights ent2 = new Flights("UPD1", "Moskow", "Domodedovo", new Date(), 250, 199);
		administratorDAO.saveFlight(ent);
		administratorDAO.saveFlight(ent2);
		List<Flights> list = administratorDAO.getAllFlights(new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
		assertEquals(2, list.size());
	}
	
	

}

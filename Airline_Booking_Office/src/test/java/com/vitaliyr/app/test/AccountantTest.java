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

import com.vitaliyr.app.dao.AccountantDAO;
import com.vitaliyr.app.entity.Orders;
import com.vitaliyr.app.entity.Reserves;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class AccountantTest {
	
	@PersistenceContext
	private EntityManager em;

	
	@Inject
	AccountantDAO accountantDAO;


	@Test
	public void getAllBookedTest() {
		Orders order = new Orders(new Timestamp(new Date().getTime()), "test@test.com", "Tester",
				500.00, new ArrayList<Reserves>());
		em.persist(order);
		List<Orders> list = accountantDAO.getAllBooked(new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
		assertEquals(1, list.size());
	}
	
	@Test
	public void setSoldTest() {
		Orders order = new Orders(new Timestamp(new Date().getTime()), "test@test.com", "Tester",
				500.00, new ArrayList<Reserves>());
		em.persist(order);
		em.flush();
		int id = order.getId();
		accountantDAO.setSold(id);
		List<Orders> list = accountantDAO.getAllBooked(new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
		assertEquals(0, list.size());
	}

}

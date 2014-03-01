package com.vitaliyr.app.test;

import static org.junit.Assert.*;

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

import com.vitaliyr.app.dao.AuthorizationDAO;
import com.vitaliyr.app.dao.SecurityOfficerDAO;
import com.vitaliyr.app.entity.Staff;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class SecurityOfficerTest {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	SecurityOfficerDAO securityOfficerDAO;
	
	@Inject
	AuthorizationDAO authorizatioDAO;

	

	@Test
	public void getAllStaffTest() {
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		Staff stf2 = new Staff("stf2", AuthorizationDAO.getHash("stf2"), "stf2", "stf2@mail.net", (short)2);
		em.persist(stf1);
		em.persist(stf2);
		List<Staff> list = securityOfficerDAO.getAllStaff();
		assertEquals(2, list.size());
	}
	
	@Test
	public void saveAccountTest() {
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		int id = securityOfficerDAO.saveAccount(stf1);
		Staff stf2 = em.find(Staff.class, id);
		assertEquals("stf1", stf2.getLogin());
	}
	
	@Test
	public void checkUserTest() {
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		em.persist(stf1);
		assertEquals(false, securityOfficerDAO.checkUser("stf1"));
		assertEquals(true, securityOfficerDAO.checkUser("stf2"));
	}
	
	@Test
	public void deleteAccountTest() {
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		em.persist(stf1);
		em.flush();
		int id = stf1.getId();
		securityOfficerDAO.deleteAccount(id);
		Staff stf2 = em.find(Staff.class, id);
		assertEquals(null, stf2);
	}
	
	

}

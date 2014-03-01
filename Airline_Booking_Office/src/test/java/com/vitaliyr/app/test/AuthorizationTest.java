package com.vitaliyr.app.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

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
public class AuthorizationTest {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	AuthorizationDAO authorizationDAO;
	
	@Inject
	SecurityOfficerDAO securityOfficerDAO;


	@Test
	public void loginUserTest() {
		
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		em.persist(stf1);
		
		Map<Staff, Boolean> map = authorizationDAO.loginUser("stf1", "stf1");
		Set<Map.Entry<Staff, Boolean>> mapSet = map.entrySet();
		Map.Entry<Staff, Boolean> temp = mapSet.iterator().next();
		Staff stf2 = temp.getKey();
		boolean lastLogin = temp.getValue();
		
		assertEquals("stf1", stf2.getLogin());
		assertEquals(false, lastLogin);
		
	}
	
	@Test
	public void changePasswordTest() {
		
		Staff stf1 = new Staff("stf1", AuthorizationDAO.getHash("stf1"), "stf1", "stf1@mail.net", (short)1);
		em.persist(stf1);
		em.flush();
		int id = stf1.getId();
		authorizationDAO.changePassword(id, "stf2");
		
		Map<Staff, Boolean> map = authorizationDAO.loginUser("stf1", "stf2");
		Set<Map.Entry<Staff, Boolean>> mapSet = map.entrySet();
		Map.Entry<Staff, Boolean> temp = mapSet.iterator().next();
		Staff stf2 = temp.getKey();
		boolean lastLogin = temp.getValue();
		
		assertEquals("stf1", stf2.getLogin());
		assertEquals(true, lastLogin);
		
	}

}

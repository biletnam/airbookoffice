package com.vitaliyr.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.vitaliyr.app.entity.Staff;

@Repository
public class SecurityOfficerDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Staff> getAllStaff() {
		List<Staff> list = em.createQuery("SELECT s FROM Staff s WHERE s.role != 0", Staff.class).getResultList();
		return list;
	}

	public int saveAccount(Staff ent) {
		Staff toPersist = ent;
		toPersist.setPassword(AuthorizationDAO.getHash(toPersist.getPassword()));
		em.persist(toPersist);
		em.flush();
		return toPersist.getId();
	}
	
	public boolean checkUser(String login) {
		
		TypedQuery<Staff> query = em.createQuery("FROM Staff s WHERE s.login = :login", Staff.class);
		query.setParameter("login", login);
		List<Staff> list = query.getResultList();
		
		if (list.isEmpty()) {
			return true;
		}
		
		return false;
		
	}

	
	public void updateStaff(Staff ent) {
		em.merge(ent);
	}


	public void deleteAccount(int id) {
		Staff todelete = em.find(Staff.class, id);
		em.remove(todelete);
	}

	public SecurityOfficerDAO() {
	}

}

package com.vitaliyr.app.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vitaliyr.app.entity.Staff;

@Repository
public class AuthorizationDAO {
	
	
	@PersistenceContext
	private EntityManager em;
	
	/*
	 * Login
	 */

	public java.util.Map<Staff, Boolean> loginUser(String login,
			String password) {
		Staff user = null;
		java.util.Map<Staff, Boolean> result = new java.util.HashMap<Staff, Boolean>();
		Query query = em.createQuery("SELECT s FROM Staff s WHERE s.login = ?1 AND s.password = ?2", Staff.class);

		query.setParameter(1, login);
		query.setParameter(2, getHash(password));
		try {
		user = (Staff) query.getSingleResult();
		}
		catch (javax.persistence.NoResultException e) {
			user = null;
		}
		

		if (user == null) {
			result.put(null, false);
			return result;
		}

		if (user.getLastlogin() == null) {
			result.put(user, false);
			return result;
		}

		user.setLastlogin(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));

		result.put(user, true);

		return result;
	}
	
	
	

	/*
	 * User password change;
	 */

	public void changePassword(int id, String newpassword) {

		Staff user = em.find(Staff.class, id);
		
		user.setPassword(getHash(newpassword));
		user.setLastlogin(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
	}

	/*
	 * Generate MD5 Hash
	 */

	public static String getHash(String input) {

		String md5 = null;

		if (null == input)
			return null;

		try {

			MessageDigest digest = MessageDigest.getInstance("MD5");

			digest.update(input.getBytes(), 0, input.length());

			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}

package com.vitaliyr.app.service;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.AuthorizationDAO;
import com.vitaliyr.app.entity.Staff;

@Named
public class AuthorizationService {
	@Inject
	private AuthorizationDAO authorizationDAO;

	@Transactional
	public Map<Staff, Boolean> loginUser(String login, String password) {
		return authorizationDAO.loginUser(login, password);
	}

	@Transactional
	public boolean changePassword(Staff user, String login, String password,
			String newPassword) {

		if (user.getLogin().equals(login) && user.getPassword().equals(AuthorizationDAO.getHash(password)) && !(password.equals(newPassword))) {
			authorizationDAO.changePassword(user.getId(), newPassword);
			return true;
		}

		return false;
	}

}

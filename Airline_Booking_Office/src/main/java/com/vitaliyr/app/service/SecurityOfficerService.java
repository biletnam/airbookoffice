package com.vitaliyr.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.vitaliyr.app.dao.SecurityOfficerDAO;
import com.vitaliyr.app.entity.Staff;


@Named
public class SecurityOfficerService {
	
	@Inject
	SecurityOfficerDAO securityOfficerDAO;
	
	@Transactional
	public List<Staff> getAllStaff() {
		return securityOfficerDAO.getAllStaff();
	}
	
	
	@Transactional
	public boolean addAccount(String login, String password, String empname, String empmail, short role) {
		
		if (securityOfficerDAO.checkUser(login)) {
			Staff staff = new Staff(login, password, empname, empmail, role);
			securityOfficerDAO.saveAccount(staff);
			return true;
		}
		
		return false;
		
	}
	
	@Transactional
	public void deleteAccount(int id) {
		securityOfficerDAO.deleteAccount(id);
	}
	
	@Transactional
	public void updateAccount(Staff ent) {
		securityOfficerDAO.updateStaff(ent);
	}
	
}

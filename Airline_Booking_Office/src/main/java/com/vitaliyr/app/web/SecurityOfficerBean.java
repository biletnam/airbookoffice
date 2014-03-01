package com.vitaliyr.app.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.vitaliyr.app.entity.Staff;
import com.vitaliyr.app.service.SecurityOfficerService;

@Named("secOf")
@Scope("session")
public class SecurityOfficerBean implements Serializable {
	
	
	@Inject
	SecurityOfficerService securityOfficerService;
	
	private static final long serialVersionUID = 1L;


	private List<Staff> stList;

	
	public String showStaff() {
		stList = securityOfficerService.getAllStaff();
		return null;

	}
	
	
	/*
	 * DataTable operations
	 */
	public String editRole(Staff s) {
		s.setEditable(true);
		return null;
	}
	
	
	public String saveStaffItem(Staff s) {
		securityOfficerService.updateAccount(s);
		s.setEditable(false);
		return null;
	}
	
	
	public String deleteStaff(Staff s) {
		stList.remove(s);
		securityOfficerService.deleteAccount(s.getId());
		return null;
	}
	
	public String cancelEditingStaffItem(Staff s) {
		s.setEditable(false);
		return null;
	}
	

	public List<Staff> getStList() {
		return stList;
	}

	public void setStList(ArrayList<Staff> stList) {
		this.stList = stList;
	}

	public SecurityOfficerBean() {
	}
	
	

}

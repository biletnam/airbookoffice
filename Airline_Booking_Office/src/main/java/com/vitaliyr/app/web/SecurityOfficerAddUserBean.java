package com.vitaliyr.app.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.service.SecurityOfficerService;

@Named("secOfAdd")
@Scope("request")
public class SecurityOfficerAddUserBean {
	
	@Inject
	SecurityOfficerService securityOfficerService;
	
	
	private String login;
	private String password;
	private String empname;
	private String empmail;
	private short role;
	
	
	public String addNewStaff() {
		if(securityOfficerService.addAccount(login, password, empname, empmail, role)) {
			return "securityOfficer";
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"User already exists", "User already exists"));
		return null;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpmail() {
		return empmail;
	}
	public void setEmpmail(String empmail) {
		this.empmail = empmail;
	}
	public short getRole() {
		return role;
	}
	public void setRole(short role) {
		this.role = role;
	}
	public SecurityOfficerAddUserBean() {
	}
	
	
	
	

}

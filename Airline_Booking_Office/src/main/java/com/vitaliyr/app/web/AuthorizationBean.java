package com.vitaliyr.app.web;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.entity.Staff;
import com.vitaliyr.app.service.AuthorizationService;

@Named("loginBean")
@Scope("session")
public class AuthorizationBean implements Serializable {

	@Inject
	AuthorizationService authorizationService;

	private static final long serialVersionUID = 1L;
	
	private static final short SECURITY_OFFICER = 0;
	private static final short ADMINISTRATOR = 1;
	private static final short ACCOUNTANT = 2;
	private static final short BUSINESS_ANALYTIC = 3;
	

	private String login;
	private String password;
	private Staff user;
	private boolean lastLoginCheck;
	private boolean logined;

	/*
	 * Log In method
	 */
	public String logIn() {

		Map<Staff, Boolean> map = authorizationService.loginUser(login,
				password);

		Set<Map.Entry<Staff, Boolean>> mapSet = map.entrySet();
		Map.Entry<Staff, Boolean> temp = mapSet.iterator().next();
		user = temp.getKey();
		lastLoginCheck = temp.getValue();

		if (user != null) {
			switch (user.getRole()) {
			case SECURITY_OFFICER: {
				logined = true;
				return "securityOfficer";
			}
			case ADMINISTRATOR: {
				logined = true;
				return "administrator";
			}
			case ACCOUNTANT: {
				logined = true;
				return "accountant";
			}
			case BUSINESS_ANALYTIC: {
				logined = true;
				return "analytic";
			}
			}
		}

		return "errorLogin";
	}

	/*
	 * Verify user
	 */
	public String verifyUser() {
		if (user != null) {
			switch (user.getRole()) {
			case SECURITY_OFFICER:
				if (logined) {
					return "securityOfficer";
				}
			case ADMINISTRATOR:
				if (logined) {
					return "administrator";
				}
			case ACCOUNTANT:
				if (logined) {
					return "accountant";
				}
			case BUSINESS_ANALYTIC:
				if (logined) {
					return "analytic";
				}
			}
		}
		return "errorAccess";
	}

	/*
	 * Log Off methods
	 */
	public String logOffFromMain() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "main";
	}

	public String logOff() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "loggedOff";
	}
	

	/*
	 * Getters & Setters
	 */
	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
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

	public boolean isLastLoginCheck() {
		return lastLoginCheck;
	}

	public void setLastLoginCheck(boolean lastLoginCheck) {
		this.lastLoginCheck = lastLoginCheck;
	}

	public Staff getUser() {
		return user;
	}

	public AuthorizationBean() {
	}

}

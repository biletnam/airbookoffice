package com.vitaliyr.app.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.service.AuthorizationService;

@Named("changePass")
@Scope("request")
public class ChangePasswordBean {

	private String login;
	private String password;
	private String newPassword;
	private boolean changed;

	@Inject
	AuthorizationBean authorizationBean;
	
	@Inject
	AuthorizationService authorizationService;

	/*
	 * Change password method
	 */

	public String changePassword() {
		
		if (authorizationService.changePassword(authorizationBean.getUser(), login, password, newPassword)) {
			changed = true;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.removeAttribute("loginBean");
			return null;
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login wrong or new password is the same", "Change password error"));
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

}

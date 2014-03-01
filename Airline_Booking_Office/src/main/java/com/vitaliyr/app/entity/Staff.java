package com.vitaliyr.app.entity;

import javax.persistence.*;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String login;
	private String password;
	private String empname;
	private String empmail;
	private short role;
	private java.sql.Timestamp lastlogin;

	@Transient
	private boolean selected;
	@Transient
	private boolean editable;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public java.sql.Timestamp getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(java.sql.Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Staff() {
	}

	public Staff(String login, String password, String empname, String empmail,
			short role) {
		this.login = login;
		this.password = password;
		this.empname = empname;
		this.empmail = empmail;
		this.role = role;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", login=" + login + ", password="
				+ password + ", empname=" + empname + ", empmail=" + empmail
				+ ", role=" + role + ", lastlogin=" + lastlogin + "]";
	}

}

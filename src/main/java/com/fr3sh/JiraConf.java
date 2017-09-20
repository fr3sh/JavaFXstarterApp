package com.fr3sh;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class JiraConf {

	private SimpleStringProperty login = new SimpleStringProperty();
	private String pass;
	ArrayList<Integer> weekHodj;
	Boolean submit = false;

	public JiraConf() {
		setWeekHodj(new ArrayList<Integer>());

	}

	public String getLogin() {
		return login.get();
	}
	
	public SimpleStringProperty loginProperty() {
		return login;
	}

	public void setLogin(String login) {
		this.login.set(login); 
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public ArrayList<Integer> getWeekHodj() {
		return weekHodj;
	}

	public void setWeekHodj(ArrayList<Integer> weekHodj) {
		this.weekHodj = weekHodj;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

}

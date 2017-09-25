package com.fr3sh;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JiraConf {

	private SimpleStringProperty login = new SimpleStringProperty();
	private SimpleStringProperty pass = new SimpleStringProperty();
	private ObservableList<Integer> weekObs;
	private ListProperty<Integer> weekProperty;
	
	private ObjectProperty<Integer> pnProp ;
	
	public ObjectProperty<Integer> getPnProp() {
		return pnProp;
	}

	public void setPnProp(ObjectProperty<Integer> pnProp) {
		this.pnProp = pnProp;
	}

	private IntegerProperty pnintegerProperty;
	
	
	public ListProperty<Integer> getWeekProperty() {
		return weekProperty;
	}

	public void setWeekProperty(ListProperty<Integer> weekProperty) {
		this.weekProperty = weekProperty;
	}

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
		return pass.get();
	}

	public void setPass(String pass) {
		this.pass.set(pass);
	}

	public SimpleStringProperty passProperty() {
		return pass;
	}
	
	public ArrayList<Integer> getWeekHodj() {
		return weekHodj;
	}

	public void setWeekHodj(ArrayList<Integer> weekHodj) {
		this.weekHodj = weekHodj;
		//weekObs = FXCollections.observableArrayList(weekHodj);
		//weekProperty.set(weekObs);;
		
	
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}
	
	public void setPROPforLIST() {
		pnProp= new SimpleObjectProperty<>(weekHodj.get(1));
		pnintegerProperty= IntegerProperty.integerProperty(pnProp);
	}

}

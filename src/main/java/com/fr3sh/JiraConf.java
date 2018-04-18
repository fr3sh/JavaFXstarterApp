package com.fr3sh;

import java.util.ArrayList;
import javax.xml.ws.Service;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JiraConf {

	private SimpleStringProperty login = new SimpleStringProperty();
	private SimpleStringProperty pass = new SimpleStringProperty();
	private ObservableList<Integer> weekObs;
	private ListProperty<Integer> weekProperty;
	
	private ObjectProperty<Integer> pnProp ;
	
	private IntegerProperty pnintegerProperty;
	private IntegerProperty wtintegerProperty;
	private IntegerProperty srintegerProperty;
	private IntegerProperty czwintegerProperty;
	private IntegerProperty ptintegerProperty;
	private ObjectProperty<Integer> wtProp ;
	private ObjectProperty<Integer> srProp ;
	private ObjectProperty<Integer> czwProp ;
	private ObjectProperty<Integer> ptProp ;
	
	ArrayList<Integer> weekHodj;
	//Boolean submit = false;

	private SimpleBooleanProperty submitProp = new SimpleBooleanProperty();
		
		
	public Boolean getSubmit() {
		return submitProp.get();
	}
	
	public SimpleBooleanProperty getSubmitPropProperty() {
		return submitProp;
	}

	public void setSubmitProp(Boolean ss) {
		this.submitProp.set(ss); 
	}
	
	public ObjectProperty<Integer> getWtProp() {
		return wtProp;
	}

	public void setWtProp(ObjectProperty<Integer> wtProp) {
		this.wtProp = wtProp;
	}

	public ObjectProperty<Integer> getSrProp() {
		return srProp;
	}

	public void setSrProp(ObjectProperty<Integer> srProp) {
		this.srProp = srProp;
	}

	public ObjectProperty<Integer> getCzwProp() {
		return czwProp;
	}

	public void setCzwProp(ObjectProperty<Integer> czwProp) {
		this.czwProp = czwProp;
	}

	public ObjectProperty<Integer> getPtProp() {
		return ptProp;
	}

	public void setPtProp(ObjectProperty<Integer> ptProp) {
		this.ptProp = ptProp;
	}


	public ObjectProperty<Integer> getPnProp() {
		return pnProp;
	}

	public void setPnProp(ObjectProperty<Integer> pnProp) {
		this.pnProp = pnProp;
	}

	public Integer getPn() {
		return pnProp.get();
	}
	
	public Integer getWt() {
		return wtProp.get();
	}
	
	public Integer getSr() {
		return srProp.get();
	}
	public Integer getCzw() {
		return czwProp.get();
	}
	public Integer getPt() {
		return ptProp.get();
	}
	
	
	public ListProperty<Integer> getWeekProperty() {
		return weekProperty;
	}

	public void setWeekProperty(ListProperty<Integer> weekProperty) {
		this.weekProperty = weekProperty;
	}



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

/*	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}*/
	
	public void setPROPforLIST() {
		pnProp= new SimpleObjectProperty<>(weekHodj.get(0));
		pnintegerProperty= IntegerProperty.integerProperty(pnProp);
		
		wtProp= new SimpleObjectProperty<>(weekHodj.get(1));
		wtintegerProperty= IntegerProperty.integerProperty(wtProp);
		
		srProp= new SimpleObjectProperty<>(weekHodj.get(2));
		srintegerProperty= IntegerProperty.integerProperty(srProp);
		
		czwProp= new SimpleObjectProperty<>(weekHodj.get(3));
		czwintegerProperty= IntegerProperty.integerProperty(czwProp);
		
		ptProp= new SimpleObjectProperty<>(weekHodj.get(4));
		ptintegerProperty= IntegerProperty.integerProperty(ptProp);
	}

}

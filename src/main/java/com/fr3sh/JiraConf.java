package com.fr3sh;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class JiraConf implements Serializable{

	private static final long serialVersionUID = 1L;
	
	transient private SimpleStringProperty login = new SimpleStringProperty();
	transient private SimpleStringProperty pass = new SimpleStringProperty();
	transient private ObservableList<Integer> weekObs;
	transient private ListProperty<Integer> weekProperty;
	
	transient private ObjectProperty<Integer> pnProp = new SimpleObjectProperty<>(0);
	
	transient private IntegerProperty pnintegerProperty = IntegerProperty.integerProperty(pnProp);

	transient private ObjectProperty<Integer> wtProp = new SimpleObjectProperty<>(0);
	transient private ObjectProperty<Integer> srProp =new SimpleObjectProperty<>(0);  
	transient private ObjectProperty<Integer> czwProp =new SimpleObjectProperty<>(0);
	transient private ObjectProperty<Integer> ptProp =new SimpleObjectProperty<>(0); 
	
	transient private IntegerProperty wtintegerProperty = IntegerProperty.integerProperty(wtProp);;
	transient private IntegerProperty srintegerProperty = IntegerProperty.integerProperty(srProp);;
	transient private IntegerProperty czwintegerProperty = IntegerProperty.integerProperty(czwProp);
	transient private IntegerProperty ptintegerProperty =IntegerProperty.integerProperty(ptProp);
	
	private String SerializableLogin ="";
	private String SerializablePass ="";
	
	private Integer SerializablePN = 0;
	private Integer SerializableWT = 0;
	private Integer SerializableSR = 0;
	private Integer SerializableCZ = 0;
	private Integer SerializablePT = 0;
	private Boolean SerializableSubmit = false;
	
	
	 private void writeObject(ObjectOutputStream out) throws IOException
	    {
		 SerializableLogin = this.getLogin();
		 SerializablePass = this.getPass();
		
		 
		 SerializablePN =this.getPn();
		 SerializableWT =this.getWt();
		 SerializableSR =this.getSr();
		 
		 SerializableCZ =this.getCzw();
		 SerializablePT =this.getPt();
		 SerializableSubmit = this.getSubmit();
		 
		 out.defaultWriteObject();
	     
	     
	   }
	    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, InvalidObjectException
	    {
	     // our "pseudo-constructor"
	     in.defaultReadObject();
	     // now we are a "live" object again, so let's run rebuild and start
	     this.login =  new SimpleStringProperty();
	     this.pass =  new SimpleStringProperty();
	   //  setWeekHodj(new ArrayList<Integer>());
	           this.setLogin(this.SerializableLogin); 
	           this.setPass(this.SerializablePass);
	           setPROPforLISTfromSerializable();
	           
	           this.submitProp = new SimpleBooleanProperty();      
               this.setSubmitProp(this.SerializableSubmit);
     
	           
	  }
	
	
	
	ArrayList<Integer> weekHodj;
	//Boolean submit = false;

	transient private SimpleBooleanProperty submitProp = new SimpleBooleanProperty();
		
		
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
	
	public void setPROPforLISTfromSerializable() {
		
		weekHodj = new ArrayList<>();
		pnProp= new SimpleObjectProperty<>(SerializablePN);
		pnintegerProperty= IntegerProperty.integerProperty(pnProp);
		weekHodj.add(SerializablePN);
		
		wtProp= new SimpleObjectProperty<>(SerializableWT);
		wtintegerProperty= IntegerProperty.integerProperty(wtProp);
		weekHodj.add(SerializableWT);
		
		srProp= new SimpleObjectProperty<>(SerializableSR);
		srintegerProperty= IntegerProperty.integerProperty(srProp);
		weekHodj.add(SerializableSR);
		
		czwProp= new SimpleObjectProperty<>(SerializableCZ);
		czwintegerProperty= IntegerProperty.integerProperty(czwProp);
		weekHodj.add(SerializableCZ);
		
		ptProp= new SimpleObjectProperty<>(SerializablePT);
		ptintegerProperty= IntegerProperty.integerProperty(ptProp);
		weekHodj.add(SerializablePT);
	}

}

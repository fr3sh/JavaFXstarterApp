package com.fr3sh.helper;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

public class BindedObjects {
	
	
	TextField a =  new TextField();
	SimpleStringProperty b = new SimpleStringProperty();
	
	
	public TextField getA() {
		return a;
	}
	public void setA(TextField a) {
		this.a = a;
	}
	public SimpleStringProperty getB() {
		return b;
	}
	public void setB(SimpleStringProperty b) {
		this.b = b;
	}
	
	

}

package com.fr3sh;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;


public class JiraParam {
	
	
	private SimpleStringProperty nazwa = new SimpleStringProperty();
	private SimpleStringProperty link = new SimpleStringProperty();
	//private String nazwa;
	//private String link;
	

	//private ArrayList<String> options;
	private ArrayList<SimpleStringProperty> options = new ArrayList<SimpleStringProperty>();
	private ArrayList<ObjectProperty<Integer>> h = new ArrayList<ObjectProperty<Integer>>();
	//private ArrayList<Integer> h;
	//private String important;
	private SimpleStringProperty  important = new SimpleStringProperty();
	private SimpleBooleanProperty imp1 = new SimpleBooleanProperty();
	
	
	public SimpleBooleanProperty getImp1() {
		return imp1;
	}



	public void setImp1(SimpleBooleanProperty imp1) {
		this.imp1 = imp1;
		if (imp1.get()) {
			setImportant2("y");
		}
		else {
			
				setImportant2("n");
		
		}
	}



	public  JiraParam() {
		//setOptions(new ArrayList<String>());
		//setH(new ArrayList<Integer>());
	}
	


@Override
public String toString() {
	System.out.println("Nazwa:" +getNazwa());
	System.out.println("link:" +getLink());
	
	String temp1 = "";
	for (int i = 0; i < getOptions().size(); i++) {
		temp1 = temp1 + getOptions().get(i) + "##";
	}
	System.out.println("OPTIONS"+ temp1);
	//System.out.println("H:" +h);
	System.out.println("Important:" +getImportant());
	
	return super.toString();
}

public String getImportant() {
	return important.get();
}

public void setImportant(String important) {
	this.important.set(important);
	
	if (important.equals("y")) {
	this.imp1.set(true);
	}else {
		this.imp1.set(false);
	}
}

public void setImportant2(String important) {
	this.important.set(important);
}

public SimpleStringProperty importantProperty() {
	return important;
}



/*public ArrayList<Integer> getH() {
	return h;
}

public void setH(ArrayList<Integer> arrayList) {
	this.h = arrayList;
}*/




public String getNazwa() {
	return nazwa.get();
}

public SimpleStringProperty nazwaProperty() {
	return nazwa;
}

public void setNazwa(String nazwa) {
	this.nazwa.set(nazwa); 
}



/*public String getNazwa() {
	return nazwa;
}

public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}*/

/*public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}*/



public String getLink() {
	return link.get();
}

public SimpleStringProperty linkProperty() {
	return link;
}

public void setLink(String link) {
	this.link.set(link); 
}

public ArrayList<Integer> getH() {
	ArrayList<Integer> opt = new ArrayList<Integer>();
	for (int i = 0; i < this.h.size(); i++) {
		
		opt.add(this.h.get(i).getValue());
	}
		
	return opt;
}

public ArrayList<ObjectProperty<Integer>> hProperty() {
	return h;
}

public void setH(ArrayList<Integer> arrayList) {
	for (int i = 0; i < arrayList.size(); i++) {
		this.h.add(new SimpleObjectProperty<>(arrayList.get(i)));
	}
}

public void addH(Integer temp1) {
		this.h.add(new  SimpleObjectProperty<>(temp1));
}

public ArrayList<String> getOptions() {
	
	ArrayList<String> opt = new ArrayList<String>();
	for (int i = 0; i < this.options.size(); i++) {
		String g = this.options.get(i).getValue();
		opt.add(this.options.get(i).getValue());
		//opt.add(this.options.get(i).toString());
	}
	//this.options.
	
	return opt;
}

public ArrayList<SimpleStringProperty> OptionsProperty() {
	return options;
}

public void setOptions(ArrayList<String> arrayList) {
	//this.options = arrayList;
	for (int i = 0; i < arrayList.size(); i++) {
		//this.options.set(i,  new SimpleStringProperty(arrayList.get(i)) );
		this.options.add(new SimpleStringProperty(arrayList.get(i)));
	}
	//this.options.set(index, element)
}

public void addOptions(String temp1) {
	//this.options = arrayList;
	
		this.options.add(new SimpleStringProperty(temp1));

	//this.options.set(index, element)
}

}

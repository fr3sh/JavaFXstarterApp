package com.fr3sh;

import java.util.ArrayList;


public class JiraParam {
	
	private String nazwa;
	private String link;
	private ArrayList<String> options;
	private ArrayList<Integer> h;
	private String important;
	
	
	public  JiraParam() {
		setOptions(new ArrayList<String>());
		setH(new ArrayList<Integer>());
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

public String getNazwa() {
	return nazwa;
}

public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}

public String getLink() {
	return link;
}

public void setLink(String link) {
	this.link = link;
}

public ArrayList<String> getOptions() {
	return options;
}

public void setOptions(ArrayList<String> arrayList) {
	this.options = arrayList;
}

public String getImportant() {
	return important;
}

public void setImportant(String important) {
	this.important = important;
}

public ArrayList<Integer> getH() {
	return h;
}

public void setH(ArrayList<Integer> arrayList) {
	this.h = arrayList;
}
}

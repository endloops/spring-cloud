package com.cesi.actuatorSecond.test.observer.interfaces.impl;

import java.util.Observable;
import java.util.Observer;

public class SchoolObserable extends Observable{
	
	private String Name;
	
	
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	private Object dateObject;

	public Object getDateObject() {
		return dateObject;
	}

	public void setDateObject(Object dateObject) {
		
		this.dateObject = dateObject;
		super.setChanged();
		super.notifyObservers(dateObject);
	}

	public SchoolObserable(WeatherDateObject dateObject) {
		super();
		this.dateObject = dateObject;
	}

	public SchoolObserable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

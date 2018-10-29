package com.cesi.actuatorSecond.test.observer.interfaces.impl;

import java.util.Observable;
import java.util.Observer;

public class TeacherObserverAble extends Observable implements Observer{
	
	private SchoolObserable obserable;
	
	public TeacherObserverAble(SchoolObserable arg) {
		this.obserable=arg;
		obserable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof WeatherDateObject){
			System.out.println("接收到:"+o.getClass()+",消息:"+arg.toString());
			getMessage(arg);
		}else{
			System.out.println("发了"+arg+"钱");
		}
	}
	
	public void getMessage(Object arg){
		super.setChanged();
		super.notifyObservers(arg);
	}

}

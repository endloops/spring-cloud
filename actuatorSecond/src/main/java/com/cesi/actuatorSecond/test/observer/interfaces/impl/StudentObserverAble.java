package com.cesi.actuatorSecond.test.observer.interfaces.impl;

import java.util.Observable;
import java.util.Observer;

public class StudentObserverAble implements Observer{

	private Observable obserable;
	
	public StudentObserverAble(Observable arg) {
		this.obserable=arg;
		obserable.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("接收到:"+o.getClass()+",消息:"+arg.toString());
	}

}

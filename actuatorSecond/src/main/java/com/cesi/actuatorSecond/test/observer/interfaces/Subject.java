package com.cesi.actuatorSecond.test.observer.interfaces;

import java.util.Observer;

public interface Subject {
	public void registerObserver(Observer ob);
	
	public void removeObserver(Observer ob);
	
	public void notifyObservers();
}

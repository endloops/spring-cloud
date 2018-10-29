package com.cesi.actuatorSecond.test.observer.interfaces.listener;

import java.util.Calendar;

public class ThreadTest extends Thread {
	int pauseTime;
	String name;

	public ThreadTest(int time,String n){
	pauseTime = time;
	name = n;
	}

	public void run() {
		Calendar now;
		now = Calendar.getInstance();
		System.out.println(name + now.get(Calendar.MINUTE) + now.get(Calendar.SECOND));
		try {
			Thread.sleep(pauseTime);
		} catch (Exception e) {
			e.getStackTrace();
		}
		Threadtest2 myt1 = new Threadtest2(5000, "threadin;;");
		myt1.start();
	}
}

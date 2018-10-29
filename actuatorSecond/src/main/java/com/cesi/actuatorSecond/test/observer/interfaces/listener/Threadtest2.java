package com.cesi.actuatorSecond.test.observer.interfaces.listener;

import java.util.Calendar;

public class Threadtest2 extends Thread {

	private int pauseTime;

	private String name;

	public Threadtest2(int time, String n) {
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
	}

}

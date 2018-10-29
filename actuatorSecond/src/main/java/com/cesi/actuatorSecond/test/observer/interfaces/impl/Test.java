package com.cesi.actuatorSecond.test.observer.interfaces.impl;

/**
 * 观察者模式
 * @author wang
 *
 */
public class Test {
	public static void main(String[] args) throws InterruptedException {
		SchoolObserable obserable = new SchoolObserable();
		TeacherObserverAble teacherObserverAbleAndobserver = new TeacherObserverAble(obserable);
		StudentObserverAble able = new StudentObserverAble(teacherObserverAbleAndobserver);
		obserable.setDateObject(new WeatherDateObject("11111","222","3333"));
		obserable.setDateObject(new WeatherDateObject("111112","1222","32333"));
		obserable.setDateObject(new WeatherDateObject("111113","1222","33333"));
		obserable.setDateObject("111");
		obserable.setDateObject("234");
		obserable.setDateObject("678");
	}
}

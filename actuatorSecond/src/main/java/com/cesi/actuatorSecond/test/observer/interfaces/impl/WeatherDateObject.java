package com.cesi.actuatorSecond.test.observer.interfaces.impl;

public class WeatherDateObject {
	private String tiaoLing;

	private String tiaoLings;
	
	private String tiaoLingss;
	public String getTiaoLing() {
		return tiaoLing;
	}

	
	public String getTiaoLings() {
		return tiaoLings;
	}


	public void setTiaoLings(String tiaoLings) {
		this.tiaoLings = tiaoLings;
	}


	public String getTiaoLingss() {
		return tiaoLingss;
	}


	public void setTiaoLingss(String tiaoLingss) {
		this.tiaoLingss = tiaoLingss;
	}


	public void setTiaoLing(String tiaoLing) {
		this.tiaoLing = tiaoLing;
	}

	public WeatherDateObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public WeatherDateObject(String tiaoLing, String tiaoLings, String tiaoLingss) {
		super();
		this.tiaoLing = tiaoLing;
		this.tiaoLings = tiaoLings;
		this.tiaoLingss = tiaoLingss;
	}


	@Override
	public String toString() {
		return "WeatherDateObject [tiaoLing=" + tiaoLing + ", tiaoLings=" + tiaoLings + ", tiaoLingss=" + tiaoLingss
				+ "]";
	}


	
	
	
}

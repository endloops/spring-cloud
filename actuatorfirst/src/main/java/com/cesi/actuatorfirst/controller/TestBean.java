package com.cesi.actuatorfirst.controller;

import java.util.Calendar;
import java.util.Date;

public class TestBean {

	String name;
	
	String bd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	
	public static void main(String[] args) {
		String ss = new String("11.11");
		String [] sss = ss.split(".");
		System.out.println(sss);
	}
}

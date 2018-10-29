package com.cesi.actuatorSecond.test.observer.interfaces.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WeatherData{

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		JButton button = new JButton("???");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("do it");
			}
		});
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("do not do it");
				
			}
		});
		frame.getContentPane().add(BorderLayout.CENTER, button);
	}
}

package com.example.websocket.controller;

import java.io.ObjectInputStream;
import java.net.Socket;


public class LogRunner implements Runnable {
	private ObjectInputStream ois;

    public LogRunner(Socket client) {
        try {
             this.ois = new ObjectInputStream(client.getInputStream());
        } catch (Exception e) {
        	System.out.println("!!!!!!!!!!!!");
    }
}
	@Override
	public void run() {
		try {
            while (true) {
	        	 Object obj= ois.readObject();
	             System.out.println(obj.toString());           }
        } catch (Exception e) {
        	System.out.println("是不是异常了");
        } finally {
        
        }
	}

}

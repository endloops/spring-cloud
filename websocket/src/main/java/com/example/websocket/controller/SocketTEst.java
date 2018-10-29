package com.example.websocket.controller;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketTEst {
	public static void main(String[] args) throws Exception {
	 	ServerSocket socket = new ServerSocket(4560);
	    while (true) {
	    	System.out.println("启动了");
	    	Socket client = socket.accept();
	    	Thread t = new Thread(new LogRunner(client));
	    	t.start();
	    }
	}
}

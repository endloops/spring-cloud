package sxs.xas.bqq.hqz.yjgc.myw.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public static void main(String[] args) throws IOException{
		
		String ss = null;
		
	//	return ss.toString();
		
		ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
		
		queue.add("1");
		queue.add("2");
		queue.add("3");
		queue.add("4");
		
		for (String string : queue) {
			System.out.println(string);
		}
		
		queue.remove("3");
		for (String string : queue) {
			System.out.println(string);
		}
		System.out.println(queue.contains("3"));
		System.out.println(queue.remove());
		for (String string : queue) {
			System.out.println(string);
		}
		queue.add("2");
		for (String string : queue) {
			System.out.println(string);
		}
//		return "";
	}
}

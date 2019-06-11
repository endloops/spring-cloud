package sxs.xas.bqq.hqz.yjgc.myw.service;

import java.util.Timer;
import java.util.TimerTask;

public class CallVtaTimerService extends TimerTask {

	public static boolean status = false;

	public int i = 0;
	
	@Override
	public void run() {
		i++;
//		if (i == 10) {
//			status = true;
//			this.cancel();
//		}
		System.out.println("run");

	}

	public static void main(String[] args) throws InterruptedException {

		Timer timer = new Timer();

		timer.schedule(new CallVtaTimerService(), 1000, 1000);
		Thread.sleep(10000);
		timer.cancel();
		timer.purge();
		System.out.println("巴拉巴拉");
	}
}

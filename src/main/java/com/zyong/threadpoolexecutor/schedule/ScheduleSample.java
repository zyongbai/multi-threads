package com.zyong.threadpoolexecutor.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleSample {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		
		long delay = 3;
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.MINUTE, 35);
//		calendar.set(Calendar.SECOND, 0);
//		delay = calendar.getTimeInMillis() - System.nanoTime();
		
		scheduledExecutorService.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("now DateTime:" + new Date());
			}
		}, delay, TimeUnit.SECONDS);
	}
}

package com.zyong.threadpoolexecutor.schedule;

import java.util.Calendar;

public class TimeTest {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		long millis = calendar.getTimeInMillis();
		long nano = System.nanoTime();
		System.out.println("millis:" + millis);
		System.out.println("nano:" + nano);
	}
}

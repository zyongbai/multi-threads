package com.zyong.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(10);
		semaphore.tryAcquire();
		try {
			semaphore.acquire();
			
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package com.zyong.semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class SemaphoreTest {
	public static void main(String[] args) {
		MyPool myPool = new MyPool(20);
		
		// �����̳߳�
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		
		MyThread t1 = new MyThread("����A", myPool, 3);
		MyThread t2 = new MyThread("����B", myPool, 12);
		MyThread t3 = new MyThread("����C", myPool, 7);
		MyThread t4 = new MyThread("����D", myPool, 2);
		
		// ���̳߳���ִ������
		threadPool.execute(t1);
		threadPool.execute(t2);
		threadPool.execute(t3);
		threadPool.execute(t4);
		
		threadPool.shutdown();
	}
}

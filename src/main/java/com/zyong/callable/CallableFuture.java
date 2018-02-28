package com.zyong.callable;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class CallableFuture {
	public static void main(String[] args) {
		// �����̳߳�(ӵ�й̶��߳����Ŀ������̳߳�)
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// ����ӵ�з��ؽ��������
		MyCallable myCallable1 = new MyCallable("A");
		MyCallable myCallable2 = new MyCallable("B");
		
		Future<Object> future1 = executor.submit(myCallable1);
		Future<Object> future2 = executor.submit(myCallable2);
		
		try {
			System.out.println(">>>" + future1.get().toString());
			System.out.println(">>>" + future2.get().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
	}
}

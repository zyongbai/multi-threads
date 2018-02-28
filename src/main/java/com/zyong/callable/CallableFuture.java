package com.zyong.callable;

import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class CallableFuture {
	public static void main(String[] args) {
		// 创建线程池(拥有固定线程数的可重用线程池)
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// 创建拥有返回结果的任务
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

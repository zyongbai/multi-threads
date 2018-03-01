package com.zyong.threadlocal;

public class ThreadLocalMain {
	public static void main(String[] args) {
		ThreadLocal threadLocal = new ThreadLocal();
		threadLocal.get();
	}
}

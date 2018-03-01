package com.zyong.guarded.suspension;

import java.util.LinkedList;

public class RequestQueue {
	private LinkedList<Request> queue = new LinkedList<Request>();

	public synchronized Request getRequest() {
		while (queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return (Request) queue.remove();
	}

	public synchronized void addRequest(Request request) {
		queue.add(request);
		notifyAll(); // 通知getRequest()方法
	}
}

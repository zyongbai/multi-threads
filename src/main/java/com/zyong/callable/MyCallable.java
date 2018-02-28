package com.zyong.callable;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Object> {
	private String oid;

	public MyCallable(String oid) {
		this.oid = oid;
	}

	@Override
	public Object call() throws Exception {
		return oid + "返回的结果";
	}
}
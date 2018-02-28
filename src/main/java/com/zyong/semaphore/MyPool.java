package com.zyong.semaphore;

import java.util.concurrent.Semaphore;

public class MyPool {
	// –≈∫≈¡ø
	private Semaphore sp;
	
	public MyPool(int size) {
		sp = new Semaphore(size);
	}

	public Semaphore getSp() {
		return sp;
	}

	public void setSp(Semaphore sp) {
		this.sp = sp;
	}
	
}

package com.zyong.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain {
	private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final static Lock readLock = readWriteLock.readLock();
	private final static Lock writeLock = readWriteLock.writeLock();
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.tryLock();
	}
}

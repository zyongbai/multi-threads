package com.zyong.delayqueue.sample.cache;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {
	private static final Logger LOG = Logger.getLogger(Cache.class.getName());
	private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();
	private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();
	private Thread daemonThread;

	public Cache() {
		Runnable daemonTask = new Runnable() {
			public void run() {
				daemonCheck();
			}
		};

		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}

	private void daemonCheck() {
		if (LOG.isLoggable(Level.INFO))
			LOG.info("cache service started.");

		for (;;) {
			try {
				DelayItem<Pair<K, V>> delayItem = q.take();
				if (delayItem != null) {
					// ��ʱ������
					Pair<K, V> pair = delayItem.getItem();
					cacheObjMap.remove(pair.first, pair.second); // compare and remove
				}
			} catch (InterruptedException e) {
				if (LOG.isLoggable(Level.SEVERE))
					LOG.log(Level.SEVERE, e.getMessage(), e);
				break;
			}
		}

		if (LOG.isLoggable(Level.INFO))
			LOG.info("cache service stopped.");
	}

	// ��ӻ������
	public void put(K key, V value, long time, TimeUnit unit) {
		V oldValue = cacheObjMap.put(key, value);
		if (oldValue != null)
			q.remove(key);
		long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
		q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
	}

	public V get(K key) {
		return cacheObjMap.get(key);
	}
	
}

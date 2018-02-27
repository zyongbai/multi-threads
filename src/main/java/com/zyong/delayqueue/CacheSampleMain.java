package com.zyong.delayqueue;

import java.util.concurrent.TimeUnit;
import com.zyong.delayqueue.sample.cache.Cache;

public class CacheSampleMain {
	// ²âÊÔÈë¿Úº¯Êý
	public static void main(String[] args) throws Exception {
		Cache<Integer, String> cache = new Cache<Integer, String>();
		cache.put(1, "aaaa", 3, TimeUnit.SECONDS);
		cache.put(2, "bbbb", 3, TimeUnit.SECONDS);
		cache.put(3, "cccc", 3, TimeUnit.SECONDS);

		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}

		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}
	}
}

package com.zyong.cyclicbarrier;

import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * 栅栏CyclicBarrier:
 * 让一组线程到达同步点后再一起继续运行
 * @author ZHANGYONG415
 *
 */
public class CyclicBarrierSample {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// 构造方法里的数字标识有几个线程到达集合地点开始进行下一步工作
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点1，当前已有" + cb.getNumberWaiting()
								+ "个已经到达，正在等候");
						cb.await();

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点2，当前已有" + cb.getNumberWaiting()
								+ "个已经到达，正在等候");
						cb.await();
						
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("线程"
								+ Thread.currentThread().getName()
								+ "即将到达集合地点3，当前已有" + cb.getNumberWaiting()
								+ "个已经到达，正在等候");
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);

		}
		service.shutdown();
	}
}

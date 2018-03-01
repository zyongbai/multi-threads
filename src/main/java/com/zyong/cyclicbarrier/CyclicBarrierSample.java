package com.zyong.cyclicbarrier;

import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * դ��CyclicBarrier:
 * ��һ���̵߳���ͬ�������һ���������
 * @author ZHANGYONG415
 *
 */
public class CyclicBarrierSample {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		// ���췽��������ֱ�ʶ�м����̵߳��Ｏ�ϵص㿪ʼ������һ������
		final CyclicBarrier cb = new CyclicBarrier(3);
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�1����ǰ����" + cb.getNumberWaiting()
								+ "���Ѿ�������ڵȺ�");
						cb.await();

						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�2����ǰ����" + cb.getNumberWaiting()
								+ "���Ѿ�������ڵȺ�");
						cb.await();
						
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"
								+ Thread.currentThread().getName()
								+ "�������Ｏ�ϵص�3����ǰ����" + cb.getNumberWaiting()
								+ "���Ѿ�������ڵȺ�");
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

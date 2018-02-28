package com.zyong.semaphore;

public class MyThread extends Thread {
	private String threadname;// �̵߳�����
	private MyPool pool;
	private int x;// �����ź����Ĵ�С
	
	public MyThread(String threadname, MyPool pool, int x) {
		this.threadname = threadname;
		this.pool = pool;
		this.x = x;
	}
	
	@Override
	public void run() {
		try {
			// �Ӵ��ź�����ȡ������Ŀ�����
			pool.getSp().acquire(x);
			
			// todo:Ҳ����������������ӵ�ҵ��
//			Thread.sleep(3 * 1000);
			
			System.out.println(threadname + "�ɹ���ȡ��" + x + "�����!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// �ͷŸ�����Ŀ�����,���䷵�ص��ź���
			pool.getSp().release(x);
			System.out.println(threadname + "�ͷ���" + x + "�����!");
		}
	}
	
}

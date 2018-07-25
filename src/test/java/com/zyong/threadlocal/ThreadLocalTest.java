package com.zyong.threadlocal;

import org.junit.Test;
import java.io.IOException;

/**
 * @author zyong
 * @date 2018/7/25
 */
public class ThreadLocalTest {
    @Test
    public void testGetAndUpdatePerson() throws IOException {
    	final ThreadLocalDemoService demoService = new ThreadLocalDemoService();
    	
    	Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Person person = demoService.getPerson();
				System.out.println("origin person message:[" + Thread.currentThread().getName() + "][" +
                        		   person.getName() + ":" + person.getAge() + "]");
				person = demoService.updatePerson("zhangsan_thread1", 22);
				System.out.println("updated person message:[" + Thread.currentThread().getName() + "][" +
             		   			   person.getName() + ":" + person.getAge() + "]");
			}
    	});
    	
    	Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Person person = demoService.getPerson();
				System.out.println("origin person message:[" + Thread.currentThread().getName() + "][" +
                        		   person.getName() + ":" + person.getAge() + "]");
				person = demoService.updatePerson("lisi_thread2", 33);
				System.out.println("updated person message:[" + Thread.currentThread().getName() + "][" +
             		   			   person.getName() + ":" + person.getAge() + "]");
			}
    	});
    	
    	thread1.start();
    	thread2.start();
    }
}

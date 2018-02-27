package com.zyong.quartz.job;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.DisallowConcurrentExecution;

/**
 * Hello
 * @author ZHANGYONG415
 *
 */
@DisallowConcurrentExecution
public class HelloJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello Job - " + new Date());
		try {
			Thread.sleep(8 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

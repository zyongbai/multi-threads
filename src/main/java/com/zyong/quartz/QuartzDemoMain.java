package com.zyong.quartz;

import java.util.UUID;
import org.quartz.SchedulerException;
import com.zyong.quartz.job.HelloJob;
import com.zyong.quartz.util.QuartzUtils;

public class QuartzDemoMain {
	public static void main(String[] args) {
		try {
			String jobName = HelloJob.class.getName() + UUID.randomUUID();
			QuartzUtils.addJob(jobName, HelloJob.class, "0/5 * * * * ?");
			
			Thread.sleep(10 * 1000);
			
			QuartzUtils.pauseJob(jobName);
			
			System.out.println("isExists:" + QuartzUtils.checkJobIsExists(jobName));
			
			QuartzUtils.removeJob(jobName);
			
			System.out.println("now isExists:" + QuartzUtils.checkJobIsExists(jobName));
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

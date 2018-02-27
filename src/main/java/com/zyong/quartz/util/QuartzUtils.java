package com.zyong.quartz.util;

import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.JobDetail;
import org.quartz.TriggerKey;
import org.quartz.JobBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.SchedulerException;
import org.quartz.CronScheduleBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Quartz任务管理类
 * 
 * @author ZHANGYONG415
 *
 */
public class QuartzUtils {
	/** 默认任务组名称 */
	public static final String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
	/** 默认触发器组名称 */
	public static final String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";
	/** 任务调度器实例 */
	private static Scheduler scheduler;
	
	static {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			System.out.println("QuartzUtils初始化scheduler异常," + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查任务是否存在
	 * @param jobName		任务名称
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean checkJobIsExists(String jobName) throws SchedulerException {
		return scheduler.checkExists(new JobKey(jobName, JOB_GROUP_NAME));
	}
	
	/**
	 * 检查任务是否存在
	 * @param jobName		任务名称
	 * @param jobGroupName	任务组名称
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean checkJobIsExists(String jobName, String jobGroupName) throws SchedulerException {
		return scheduler.checkExists(new JobKey(jobName, jobGroupName));
	}
	
	/**
	 * 添加一个定时任务(使用cron表达式)
	 * @param jobName	任务名称
	 * @param cls		任务执行类
	 * @param cronExpression	cron表达式
	 * @throws SchedulerException 
	 */
	public static void addJob(String jobName, Class<? extends Job> cls, String cronExpression) throws SchedulerException {
		// 任务名称，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, JOB_GROUP_NAME)
										.requestRecovery(true)
										.build();
		// 创建触发器
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(jobDetail.getKey().getName(), TRIGGER_GROUP_NAME)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// 调度任务
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// 启动调度器
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * 添加一个定时任务(使用cron表达式)
	 * @param jobName				任务名称
	 * @param jobGroupName			任务组名称
	 * @param cls					任务执行类
	 * @param triggerName			触发器名称
	 * @param triggerGroupName		触发器组名称
	 * @param cronExpression		cron表达式
	 * @throws SchedulerException
	 */
	public static void addJob(String jobName, String jobGroupName, Class<? extends Job> cls, String triggerName, String triggerGroupName, String cronExpression) throws SchedulerException {
		// 任务名称，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, jobGroupName)
										.requestRecovery(true)
										.build();
		// 创建触发器
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(triggerName, triggerGroupName)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// 调度任务
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// 启动调度器
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * 添加一个定时任务(使用cron表达式)
	 * @param jobName	任务名称
	 * @param cls		任务执行类
	 * @param cronExpression	cron表达式
	 * @param object			参数对象
	 * @throws SchedulerException 
	 */
	public static void addJobAndData(String jobName, Class<? extends Job> cls, String cronExpression, Object object) throws SchedulerException {
		// 任务名称，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, JOB_GROUP_NAME)
										.requestRecovery(true)
										.build();
		// 传递参数
		jobDetail.getJobDataMap().put(jobName, object);
		// 创建触发器
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(jobDetail.getKey().getName(), TRIGGER_GROUP_NAME)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// 调度任务
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// 启动调度器
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * 添加一个定时任务(使用cron表达式)
	 * @param jobName				任务名称
	 * @param jobGroupName			任务组名称
	 * @param cls					任务执行类
	 * @param triggerName			触发器名称
	 * @param triggerGroupName		触发器组名称
	 * @param cronExpression		cron表达式
	 * @param object				参数对象
	 * @throws SchedulerException
	 */
	public static void addJobAndObject(String jobName, String jobGroupName, Class<? extends Job> cls, String triggerName, String triggerGroupName, String cronExpression, Object object) throws SchedulerException {
		// 任务名称，任务组，任务执行类
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, jobGroupName)
										.requestRecovery(true)
										.build();
		// 传递参数
		jobDetail.getJobDataMap().put(jobName, object);
		// 创建触发器
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(triggerName, triggerGroupName)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// 调度任务
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// 启动调度器
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * 暂停一个任务(实际暂停触发器)
	 * @param jobName	任务名称
	 * @throws SchedulerException 
	 */
	public static void pauseJob(String jobName) throws SchedulerException {
		// 停止触发器
		scheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
	}
	
	/**
	 * 暂停一个任务(实际暂停触发器)
	 * @param jobName	任务名称
	 * @param triggerGroupName	触发器组名称
	 * @throws SchedulerException
	 */
	public static void pauseJob(String jobName, String triggerGroupName) throws SchedulerException {
		// 停止触发器
		scheduler.pauseTrigger(new TriggerKey(jobName, triggerGroupName));
	}
	
	/**
	 * 恢复一个任务(实际恢复触发器)
	 * @param jobName	任务名称
	 * @throws SchedulerException
	 */
	public static void resumeJob(String jobName) throws SchedulerException {
		scheduler.resumeTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
	}
	
	/**
	 * 恢复一个任务(实际恢复触发器)
	 * @param jobName	任务名称
	 * @param triggerGroupName	触发器组名称
	 * @throws SchedulerException
	 */
	public static void resumeJob(String jobName, String triggerGroupName) throws SchedulerException {
		scheduler.resumeTrigger(new TriggerKey(jobName, triggerGroupName));
	}
	
	/**
	 * 删除一个任务
	 * @param jobName	任务名称
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName) throws SchedulerException {
		// 停止触发器
		scheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
		// 移除触发器
		scheduler.unscheduleJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
		// 删除任务
		scheduler.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));
	}
	
	/**
	 * 删除一个任务
	 * @param jobName				任务名称
	 * @param jobGroupName			任务组名称
	 * @param triggerGroupName		触发器组名称
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerGroupName) throws SchedulerException {
		// 停止触发器
		scheduler.pauseTrigger(new TriggerKey(jobName, triggerGroupName));
		// 移除触发器
		scheduler.unscheduleJob(new TriggerKey(jobName, triggerGroupName));
		// 删除任务
		scheduler.deleteJob(new JobKey(jobName, jobGroupName));
	}
	
	/**
	 * 启动所有的定时任务(启动调度器)
	 * @throws SchedulerException
	 */
	public static void startJobs() throws SchedulerException {
		scheduler.start();
	}
	
	/**
	 * 关闭所有定时任务(关闭调度器)
	 * @throws SchedulerException
	 */
	public static void shutdownJobs() throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}
	
}

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
 * Quartz���������
 * 
 * @author ZHANGYONG415
 *
 */
public class QuartzUtils {
	/** Ĭ������������ */
	public static final String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
	/** Ĭ�ϴ����������� */
	public static final String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";
	/** ���������ʵ�� */
	private static Scheduler scheduler;
	
	static {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			System.out.println("QuartzUtils��ʼ��scheduler�쳣," + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������Ƿ����
	 * @param jobName		��������
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean checkJobIsExists(String jobName) throws SchedulerException {
		return scheduler.checkExists(new JobKey(jobName, JOB_GROUP_NAME));
	}
	
	/**
	 * ��������Ƿ����
	 * @param jobName		��������
	 * @param jobGroupName	����������
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean checkJobIsExists(String jobName, String jobGroupName) throws SchedulerException {
		return scheduler.checkExists(new JobKey(jobName, jobGroupName));
	}
	
	/**
	 * ���һ����ʱ����(ʹ��cron���ʽ)
	 * @param jobName	��������
	 * @param cls		����ִ����
	 * @param cronExpression	cron���ʽ
	 * @throws SchedulerException 
	 */
	public static void addJob(String jobName, Class<? extends Job> cls, String cronExpression) throws SchedulerException {
		// �������ƣ������飬����ִ����
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, JOB_GROUP_NAME)
										.requestRecovery(true)
										.build();
		// ����������
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(jobDetail.getKey().getName(), TRIGGER_GROUP_NAME)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// ��������
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// ����������
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * ���һ����ʱ����(ʹ��cron���ʽ)
	 * @param jobName				��������
	 * @param jobGroupName			����������
	 * @param cls					����ִ����
	 * @param triggerName			����������
	 * @param triggerGroupName		������������
	 * @param cronExpression		cron���ʽ
	 * @throws SchedulerException
	 */
	public static void addJob(String jobName, String jobGroupName, Class<? extends Job> cls, String triggerName, String triggerGroupName, String cronExpression) throws SchedulerException {
		// �������ƣ������飬����ִ����
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, jobGroupName)
										.requestRecovery(true)
										.build();
		// ����������
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(triggerName, triggerGroupName)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// ��������
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// ����������
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * ���һ����ʱ����(ʹ��cron���ʽ)
	 * @param jobName	��������
	 * @param cls		����ִ����
	 * @param cronExpression	cron���ʽ
	 * @param object			��������
	 * @throws SchedulerException 
	 */
	public static void addJobAndData(String jobName, Class<? extends Job> cls, String cronExpression, Object object) throws SchedulerException {
		// �������ƣ������飬����ִ����
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, JOB_GROUP_NAME)
										.requestRecovery(true)
										.build();
		// ���ݲ���
		jobDetail.getJobDataMap().put(jobName, object);
		// ����������
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(jobDetail.getKey().getName(), TRIGGER_GROUP_NAME)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// ��������
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// ����������
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * ���һ����ʱ����(ʹ��cron���ʽ)
	 * @param jobName				��������
	 * @param jobGroupName			����������
	 * @param cls					����ִ����
	 * @param triggerName			����������
	 * @param triggerGroupName		������������
	 * @param cronExpression		cron���ʽ
	 * @param object				��������
	 * @throws SchedulerException
	 */
	public static void addJobAndObject(String jobName, String jobGroupName, Class<? extends Job> cls, String triggerName, String triggerGroupName, String cronExpression, Object object) throws SchedulerException {
		// �������ƣ������飬����ִ����
		JobDetail jobDetail = JobBuilder.newJob(cls)
										.withIdentity(jobName, jobGroupName)
										.requestRecovery(true)
										.build();
		// ���ݲ���
		jobDetail.getJobDataMap().put(jobName, object);
		// ����������
		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
												.withIdentity(triggerName, triggerGroupName)
												.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
												.build();
		// ��������
		if (!scheduler.isShutdown()) {
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
		// ����������
		if (!scheduler.isStarted()) {
			scheduler.start();
		}
	}
	
	/**
	 * ��ͣһ������(ʵ����ͣ������)
	 * @param jobName	��������
	 * @throws SchedulerException 
	 */
	public static void pauseJob(String jobName) throws SchedulerException {
		// ֹͣ������
		scheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
	}
	
	/**
	 * ��ͣһ������(ʵ����ͣ������)
	 * @param jobName	��������
	 * @param triggerGroupName	������������
	 * @throws SchedulerException
	 */
	public static void pauseJob(String jobName, String triggerGroupName) throws SchedulerException {
		// ֹͣ������
		scheduler.pauseTrigger(new TriggerKey(jobName, triggerGroupName));
	}
	
	/**
	 * �ָ�һ������(ʵ�ʻָ�������)
	 * @param jobName	��������
	 * @throws SchedulerException
	 */
	public static void resumeJob(String jobName) throws SchedulerException {
		scheduler.resumeTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
	}
	
	/**
	 * �ָ�һ������(ʵ�ʻָ�������)
	 * @param jobName	��������
	 * @param triggerGroupName	������������
	 * @throws SchedulerException
	 */
	public static void resumeJob(String jobName, String triggerGroupName) throws SchedulerException {
		scheduler.resumeTrigger(new TriggerKey(jobName, triggerGroupName));
	}
	
	/**
	 * ɾ��һ������
	 * @param jobName	��������
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName) throws SchedulerException {
		// ֹͣ������
		scheduler.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
		// �Ƴ�������
		scheduler.unscheduleJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
		// ɾ������
		scheduler.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));
	}
	
	/**
	 * ɾ��һ������
	 * @param jobName				��������
	 * @param jobGroupName			����������
	 * @param triggerGroupName		������������
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerGroupName) throws SchedulerException {
		// ֹͣ������
		scheduler.pauseTrigger(new TriggerKey(jobName, triggerGroupName));
		// �Ƴ�������
		scheduler.unscheduleJob(new TriggerKey(jobName, triggerGroupName));
		// ɾ������
		scheduler.deleteJob(new JobKey(jobName, jobGroupName));
	}
	
	/**
	 * �������еĶ�ʱ����(����������)
	 * @throws SchedulerException
	 */
	public static void startJobs() throws SchedulerException {
		scheduler.start();
	}
	
	/**
	 * �ر����ж�ʱ����(�رյ�����)
	 * @throws SchedulerException
	 */
	public static void shutdownJobs() throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}
	
}

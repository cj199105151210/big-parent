package com.bosssoft.bigdata.daemon.quartz.util;

import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.JOB_STATUS_NOT_RUNNING;
import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.MISFIRE_DEFAULT;
import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.MISFIRE_DO_NOTHING;
import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.MISFIRE_FIRE_AND_PROCEED;
import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.MISFIRE_IGNORE_MISFIRES;
import static com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum.SCHEDULE_JOB_KEY;
import com.bosssoft.bigdata.daemon.quartz.config.BosxQuartzFactory;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

/**
 * 定时任务的工具类
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@Component
public class TaskUtil {

	/**
	 * 获取定时任务的唯一key
	 *
	 * @param sysjob
	 * @return
	 */
	public JobKey getJobKey(SysJob sysjob) {
		return JobKey.jobKey(sysjob.getJobName(), sysjob.getJobGroup());
	}

	/**
	 * 获取定时任务触发器cron的唯一key
	 *
	 * @param sysjob
	 * @return
	 */
	public TriggerKey getTriggerKey(SysJob sysjob) {
		return TriggerKey.triggerKey(sysjob.getJobName(), sysjob.getJobGroup());
	}

	/**
	 * 添加或更新定时任务
	 *
	 * @param sysjob
	 * @param scheduler
	 */
	public void addOrUpateJob(SysJob sysjob, Scheduler scheduler) {
		CronTrigger trigger = null;
		try {
			JobKey jobKey = this.getJobKey(sysjob);
			//获得触发器
			TriggerKey triggerKey = this.getTriggerKey(sysjob);
			trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			//判断触发器是否存在（如果存在说明之前运行过但是在当前被禁用了，如果不存在说明一次都没运行过）
			if (trigger == null) {
				//新建一个工作任务 指定任务类型为串接进行的
				JobDetail jobDetail = JobBuilder.newJob(BosxQuartzFactory.class).withIdentity(jobKey).build();
				//将任务信息添加到任务信息中
				jobDetail.getJobDataMap().put(SCHEDULE_JOB_KEY.getType(), sysjob);
				//将cron表达式进行转换
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysjob.getCronExpression());
				cronScheduleBuilder = this.handleCronScheduleMisfirePolicy(sysjob, cronScheduleBuilder);
				//创建触发器并将cron表达式对象给塞入
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
				//在调度器中将触发器和任务进行组合
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysjob.getCronExpression());
				cronScheduleBuilder = this.handleCronScheduleMisfirePolicy(sysjob, cronScheduleBuilder);
				//按照新的规则进行
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
				//将任务信息更新到任务信息中
				trigger.getJobDataMap().put(SCHEDULE_JOB_KEY.getType(), sysjob);
				//重启
				scheduler.rescheduleJob(triggerKey, trigger);
			}
			// 如任务状态为暂停
			if (sysjob.getJobStatus().equals(JOB_STATUS_NOT_RUNNING.getType())) {
				this.pauseJob(sysjob, scheduler);
			}
		} catch (SchedulerException e) {
			log.error("添加或更新定时任务，失败信息：{}", e.getMessage());
		}
	}

	/**
	 * 暂停定时任务
	 *
	 * @param sysjob
	 * @param scheduler
	 */
	public void pauseJob(SysJob sysjob, Scheduler scheduler) {
		try {
			if (scheduler != null) {
				scheduler.pauseJob(getJobKey(sysjob));
			}
		} catch (SchedulerException e) {
			log.error("暂停任务失败，失败信息：{}", e.getMessage());
		}

	}

	/**
	 * 恢复定时任务
	 *
	 * @param sysjob
	 * @param scheduler
	 */
	public void resumeJob(SysJob sysjob, Scheduler scheduler) {
		try {
			if (scheduler != null) {
				scheduler.resumeJob(getJobKey(sysjob));
			}
		} catch (SchedulerException e) {
			log.error("恢复任务失败，失败信息：{}", e.getMessage());
		}

	}

	/**
	 * 移除定时任务
	 *
	 * @param sysjob
	 * @param scheduler
	 */
	public void removeJob(SysJob sysjob, Scheduler scheduler) {
		try {
			if (scheduler != null) {
				// 停止触发器
				scheduler.pauseTrigger(getTriggerKey(sysjob));
				//移除触发器
				scheduler.unscheduleJob(getTriggerKey(sysjob));
				//删除任务
				scheduler.deleteJob(getJobKey(sysjob));
			}
		} catch (Exception e) {
			log.error("移除定时任务失败，失败信息：{}", e.getMessage());
		}
	}

	/**
	 * 启动所有运行定时任务
	 *
	 * @param scheduler
	 */
	public void startJobs(Scheduler scheduler) {
		try {
			if (scheduler != null) {
				scheduler.resumeAll();
			}
		} catch (SchedulerException e) {
			log.error("启动所有运行定时任务失败，失败信息：{}", e.getMessage());
		}
	}

	/**
	 * 停止所有运行定时任务
	 *
	 * @param scheduler
	 */
	public void pauseJobs(Scheduler scheduler) {
		try {
			if (scheduler != null) {
				scheduler.pauseAll();
			}
		} catch (Exception e) {
			log.error("暂停所有运行定时任务失败，失败信息：{}", e.getMessage());
		}
	}

	/**
	 * 获取错失执行策略方法
	 *
	 * @param sysJob
	 * @param cronScheduleBuilder
	 * @return
	 */
	private CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob sysJob, CronScheduleBuilder cronScheduleBuilder) {
		if (MISFIRE_DEFAULT.getType().equals(sysJob.getMisfirePolicy())) {
			return cronScheduleBuilder;
		} else if (MISFIRE_IGNORE_MISFIRES.getType().equals(sysJob.getMisfirePolicy())) {
			return cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
		} else if (MISFIRE_FIRE_AND_PROCEED.getType().equals(sysJob.getMisfirePolicy())) {
			return cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
		} else if (MISFIRE_DO_NOTHING.getType().equals(sysJob.getMisfirePolicy())) {
			return cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
		} else {
			return cronScheduleBuilder;
		}
	}

	/**
	 * 判断cron表达式是否正确
	 *
	 * @param cronExpression
	 * @return
	 */
	public boolean isValidCron(String cronExpression) {
		return CronExpression.isValidExpression(cronExpression);
	}
}

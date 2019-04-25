package com.bosssoft.bigdata.daemon.quartz.util;


import java.time.ZoneId;
import java.util.Date;
import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.common.core.util.SpringContextHolder;
import com.bosssoft.bigdata.daemon.quartz.constant.enums.JobTypeQuartzEnum;
import com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJobLog;
import com.bosssoft.bigdata.daemon.quartz.event.SysJobLogEvent;
import com.bosssoft.bigdata.daemon.quartz.exception.TaskException;
import com.bosssoft.bigdata.daemon.quartz.service.SysJobService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 定时任务反射工具类
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class TaskInvokUtil {
	private final ApplicationEventPublisher publisher;

	@Autowired
	private SysJobService sysJobService;

	@SneakyThrows
	public void invokMethod(SysJob sysJob, Trigger trigger) {

		ITaskInvok iTaskInvok;
		//执行开始时间
		long startTime;
		//执行结束时间
		long endTime;
		//获取执行开始时间
		startTime = System.currentTimeMillis();
		//更新定时任务表内的状态、执行时间、上次执行时间、下次执行时间等信息
		SysJob updateSysjob = new SysJob();
		updateSysjob.setJobId(sysJob.getJobId());
		//日志
		SysJobLog sysJobLog = new SysJobLog();
		sysJobLog.setJobId(sysJob.getJobId());
		sysJobLog.setJobName(sysJob.getJobName());
		sysJobLog.setJobGroup(sysJob.getJobGroup());
		sysJobLog.setJobOrder(sysJob.getJobOrder());
		sysJobLog.setJobType(sysJob.getJobType());
		sysJobLog.setExecutePath(sysJob.getExecutePath());
		sysJobLog.setClassName(sysJob.getClassName());
		sysJobLog.setMethodName(sysJob.getMethodName());
		sysJobLog.setMethodParamsValue(sysJob.getMethodParamsValue());
		sysJobLog.setCronExpression(sysJob.getCronExpression());
		sysJobLog.setTenantId(sysJob.getTenantId());
		try {
			if (StrUtil.isEmpty(sysJob.getJobType())) {
				log.info("定时任务类型无对应反射方式，反射类型为空");
				throw new TaskException("定时任务类型无对应反射方式，反射类型为空");
			}
			if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.JAVA.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvok = SpringContextHolder.getBean("javaClassTaskInvok");
				iTaskInvok.invokMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.SPRING_BEAN.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvok = SpringContextHolder.getBean("springBeanTaskInvok");
				iTaskInvok.invokMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.REST.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvok = SpringContextHolder.getBean("restTaskInvok");
				iTaskInvok.invokMethod(sysJob);
			} else if (StrUtil.isNotEmpty(sysJob.getJobType()) && JobTypeQuartzEnum.JAR.getType()
					.equals(sysJob.getJobType())) {
				iTaskInvok = SpringContextHolder.getBean("jarTaskInvok");
				iTaskInvok.invokMethod(sysJob);
			} else {
				log.info("定时任务类型无对应反射方式，反射类型:{}", sysJob.getJobType());
				throw new TaskException("");
			}
			//获取执行结束时间
			endTime = System.currentTimeMillis();
			sysJobLog.setJobMessage(BosxQuartzEnum.JOB_LOG_STATUS_SUCCESS.getDescription());
			sysJobLog.setJobLogStatus(BosxQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
			sysJobLog.setExecuteTime(String.valueOf(endTime - startTime));
			//任务表信息更新
			updateSysjob.setJobExecuteStatus(BosxQuartzEnum.JOB_LOG_STATUS_SUCCESS.getType());
			updateSysjob.setStartTime(trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setPreviousTime(trigger.getPreviousFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setNextTime(trigger.getNextFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		} catch (Throwable e) {
			//获取执行结束时间
			endTime = System.currentTimeMillis();
			log.error("定时任务执行失败，任务名称：{}；任务组名：{}，cron执行表达式：{}，执行时间：{}", sysJob.getJobName(), sysJob.getJobGroup(), sysJob.getCronExpression(), new Date());
			sysJobLog.setJobMessage(BosxQuartzEnum.JOB_LOG_STATUS_FAIL.getDescription());
			sysJobLog.setJobLogStatus(BosxQuartzEnum.JOB_LOG_STATUS_FAIL.getType());
			sysJobLog.setExecuteTime(String.valueOf(endTime - startTime));
			sysJobLog.setExceptionInfo(StrUtil.sub(e.getMessage(), 0, 2000));
			//任务表信息更新
			updateSysjob.setJobExecuteStatus(BosxQuartzEnum.JOB_LOG_STATUS_FAIL.getType());
			updateSysjob.setStartTime(trigger.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setPreviousTime(trigger.getPreviousFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			updateSysjob.setNextTime(trigger.getNextFireTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		} finally {
			publisher.publishEvent(new SysJobLogEvent(sysJobLog));
			sysJobService.updateById(updateSysjob);
		}
	}
}

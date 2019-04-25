package com.bosssoft.bigdata.daemon.quartz.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.quartz.Calendar;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Configuration
@ConditionalOnClass({Scheduler.class, SchedulerFactoryBean.class})
@EnableConfigurationProperties({QuartzProperties.class})
public class BosxQuartzConfig {
	private final QuartzProperties properties;
	private final List<SchedulerFactoryBeanCustomizer> customizers;
	private final JobDetail[] jobDetails;
	private final Map<String, Calendar> calendars;
	private final Trigger[] triggers;
	private final ApplicationContext applicationContext;

	public BosxQuartzConfig(QuartzProperties properties,
							ObjectProvider<List<SchedulerFactoryBeanCustomizer>> customizers,
							ObjectProvider<JobDetail[]> jobDetails, ObjectProvider<Map<String, Calendar>> calendars
			, ObjectProvider<Trigger[]> triggers, ApplicationContext applicationContext) {
		this.properties = properties;
		this.customizers = customizers.getIfAvailable();
		this.jobDetails = jobDetails.getIfAvailable();
		this.calendars = calendars.getIfAvailable();
		this.triggers = triggers.getIfAvailable();
		this.applicationContext = applicationContext;
	}

	@Bean
	@ConditionalOnMissingBean
	public SchedulerFactoryBean quartzScheduler() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(new AutowireCapableBeanJobFactory(
				this.applicationContext.getAutowireCapableBeanFactory()));
		if (!this.properties.getProperties().isEmpty()) {
			schedulerFactoryBean.setQuartzProperties(this.asProperties(this.properties.getProperties()));
		}

		if (this.jobDetails != null && this.jobDetails.length > 0) {
			schedulerFactoryBean.setJobDetails(this.jobDetails);
		}

		if (this.calendars != null && !this.calendars.isEmpty()) {
			schedulerFactoryBean.setCalendars(this.calendars);
		}

		if (this.triggers != null && this.triggers.length > 0) {
			schedulerFactoryBean.setTriggers(this.triggers);
		}

		this.customize(schedulerFactoryBean);
		return schedulerFactoryBean;
	}

	private Properties asProperties(Map<String, String> source) {
		Properties properties = new Properties();
		properties.putAll(source);
		return properties;
	}

	private void customize(SchedulerFactoryBean schedulerFactoryBean) {
		if (this.customizers != null) {
			Iterator var2 = this.customizers.iterator();

			while (var2.hasNext()) {
				SchedulerFactoryBeanCustomizer customizer = (SchedulerFactoryBeanCustomizer) var2.next();
				customizer.customize(schedulerFactoryBean);
			}
		}

	}

	/**
	 * 初始化监听器
	 *
	 * @return
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		return new QuartzInitializerListener();
	}

	/**
	 * 通过SchedulerFactoryBean获取Scheduler的实例
	 *
	 * @return
	 */
	@Bean
	public Scheduler scheduler() {
		return quartzScheduler().getScheduler();
	}
}

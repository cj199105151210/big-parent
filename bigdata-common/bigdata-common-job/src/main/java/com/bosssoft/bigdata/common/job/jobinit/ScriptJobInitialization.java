package com.bosssoft.bigdata.common.job.jobinit;

import java.util.Map;
import com.bosssoft.bigdata.common.job.properties.ElasticJobProperties;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;


/**
 * @author lucky
 * @date 2019/2/19
 * 脚本任务初始
 */
public class ScriptJobInitialization extends AbstractJobInitialization {

	private Map<String, ElasticJobProperties.ScriptConfiguration> scriptConfigurationMap;

	public ScriptJobInitialization(final Map<String, ElasticJobProperties.ScriptConfiguration> scriptConfigurationMap) {
		this.scriptConfigurationMap = scriptConfigurationMap;
	}

	public void init() {
		for (String jobName : scriptConfigurationMap.keySet()) {
			ElasticJobProperties.ScriptConfiguration configuration = scriptConfigurationMap.get(jobName);
			initJob(jobName, configuration.getJobType(), configuration);
		}
	}

	@Override
	public JobTypeConfiguration getJobTypeConfiguration(String jobName, JobCoreConfiguration jobCoreConfiguration) {
		ElasticJobProperties.ScriptConfiguration configuration = scriptConfigurationMap.get(jobName);
		return new ScriptJobConfiguration(jobCoreConfiguration, configuration.getScriptCommandLine());
	}
}

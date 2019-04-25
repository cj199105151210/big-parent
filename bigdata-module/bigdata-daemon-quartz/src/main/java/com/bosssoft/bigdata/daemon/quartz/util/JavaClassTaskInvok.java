package com.bosssoft.bigdata.daemon.quartz.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import cn.hutool.core.util.StrUtil;
import com.bosssoft.bigdata.daemon.quartz.constant.enums.BosxQuartzEnum;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJob;
import com.bosssoft.bigdata.daemon.quartz.exception.TaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务java class反射实现
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Component("javaClassTaskInvok")
@Slf4j
public class JavaClassTaskInvok implements ITaskInvok {
	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		Object obj = null;
		Class clazz = null;
		Method method = null;
		Object returnValue = null;
		try {
			if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
				//sysJob.getClassName() 获取字节码文件中 方法 再取出其公有方法
				//寻找名称的类文件，加载进内存 产生class对象
				clazz = Class.forName(sysJob.getClassName());
				//获取一个Object对象
				obj = clazz.newInstance();
				//获取私有有参方法 private void demo2.Person.private_str(java.lang.String)
				method = clazz.getDeclaredMethod(sysJob.getMethodName(), String.class);
				returnValue = method.invoke(obj, sysJob.getMethodParamsValue());
			} else {
				clazz = Class.forName(sysJob.getClassName());
				obj = clazz.newInstance();
				method = clazz.getDeclaredMethod(sysJob.getMethodName());
				returnValue = method.invoke(obj);
			}
			if (StrUtil.isEmpty(returnValue.toString()) || BosxQuartzEnum.JOB_LOG_STATUS_FAIL.getType().equals(returnValue.toString())) {
				log.error("定时任务javaClassTaskInvok异常,执行任务：{}", sysJob.getClassName());
				throw new TaskException("定时任务javaClassTaskInvok业务执行失败,任务：" + sysJob.getClassName());
			}
		} catch (ClassNotFoundException e) {
			log.error("定时任务java反射类没有找到,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类没有找到,执行任务：" + sysJob.getClassName());
		} catch (IllegalAccessException e) {
			log.error("定时任务java反射类异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类异常,执行任务：" + sysJob.getClassName());
		} catch (InstantiationException e) {
			log.error("定时任务java反射类异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类异常,执行任务：" + sysJob.getClassName());
		} catch (NoSuchMethodException e) {
			log.error("定时任务java反射执行方法名异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射执行方法名异常,执行任务：" + sysJob.getClassName());
		} catch (InvocationTargetException e) {
			log.error("定时任务java反射执行异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射执行异常,执行任务：" + sysJob.getClassName());
		}

	}
}

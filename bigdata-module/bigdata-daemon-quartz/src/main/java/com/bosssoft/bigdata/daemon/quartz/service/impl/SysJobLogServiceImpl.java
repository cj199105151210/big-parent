package com.bosssoft.bigdata.daemon.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.daemon.quartz.entity.SysJobLog;
import com.bosssoft.bigdata.daemon.quartz.mapper.SysJobLogMapper;
import com.bosssoft.bigdata.daemon.quartz.service.SysJobLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 定时任务执行日志表
 *
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

}

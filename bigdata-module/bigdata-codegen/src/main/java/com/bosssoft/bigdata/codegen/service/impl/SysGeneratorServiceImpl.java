package com.bosssoft.bigdata.codegen.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.codegen.entity.GenConfig;
import com.bosssoft.bigdata.codegen.mapper.SysDatasourceConfMapper;
import com.bosssoft.bigdata.codegen.mapper.SysGeneratorMapper;
import com.bosssoft.bigdata.codegen.service.SysGeneratorService;
import com.bosssoft.bigdata.codegen.util.DynamicDataSourceContextHolder;
import com.bosssoft.bigdata.codegen.util.GenUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * <p>
 * @author lucky
 * @date 2018-07-30
 */
@Service
@AllArgsConstructor
public class SysGeneratorServiceImpl implements SysGeneratorService {
	private final SysDatasourceConfMapper sysDatasourceConfMapper;
	private final SysGeneratorMapper sysGeneratorMapper;

	/**
	 * 分页查询表
	 *
	 * @param tableName 查询条件
	 * @param id
	 * @return
	 */
	@Override
	public IPage<List<Map<String, Object>>> getPage(Page page, String tableName, Integer id) {
		DynamicDataSourceContextHolder.setDataSourceType(id);
		return sysGeneratorMapper.queryList(page, tableName);
	}

	/**
	 * 生成代码
	 *
	 * @param genConfig 生成配置
	 * @return
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {
		DynamicDataSourceContextHolder.setDataSourceType(genConfig.getId());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		//查询表信息
		Map<String, String> table = queryTable(genConfig.getTableName());
		//查询列信息
		List<Map<String, String>> columns = queryColumns(genConfig.getTableName());
		//生成代码
		GenUtils.generatorCode(genConfig, table, columns, zip);
		IoUtil.close(zip);
		return outputStream.toByteArray();
	}

	private Map<String, String> queryTable(String tableName) {
		return sysGeneratorMapper.queryTable(tableName);
	}

	private List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorMapper.queryColumns(tableName);
	}
}

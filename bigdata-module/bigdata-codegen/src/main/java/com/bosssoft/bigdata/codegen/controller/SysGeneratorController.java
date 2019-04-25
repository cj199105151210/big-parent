package com.bosssoft.bigdata.codegen.controller;

import javax.servlet.http.HttpServletResponse;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.codegen.entity.GenConfig;
import com.bosssoft.bigdata.codegen.service.SysGeneratorService;
import com.bosssoft.bigdata.common.core.util.R;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成器
 * <p>
 * @author lucky
 * @date 2018-07-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class SysGeneratorController {
	private final SysGeneratorService sysGeneratorService;

	/**
	 * 列表
	 *
	 * @param tableName 参数集
	 * @param id        数据源编号
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public R getPage(Page page, String tableName, Integer id) {
		return new R<>(sysGeneratorService.getPage(page, tableName, id));
	}

	/**
	 * 生成代码
	 */
	@SneakyThrows
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) {
		byte[] data = sysGeneratorService.generatorCode(genConfig);
		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}
}

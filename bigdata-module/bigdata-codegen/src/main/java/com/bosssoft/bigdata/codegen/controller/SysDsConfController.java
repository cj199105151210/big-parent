package com.bosssoft.bigdata.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.codegen.config.DynamicDataSourceConfig;
import com.bosssoft.bigdata.codegen.entity.SysDatasourceConf;
import com.bosssoft.bigdata.codegen.service.SysDatasourceConfService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 数据源管理
 * <p>
 * @author lucky
 * @date 2019-03-31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dsconf")
public class SysDsConfController {
	private final DynamicDataSourceConfig dynamicDataSourceConfig;
	private final SysDatasourceConfService sysDatasourceConfService;

	/**
	 * 分页查询
	 *
	 * @param page              分页对象
	 * @param sysDatasourceConf 数据源表
	 * @return
	 */
	@GetMapping("/page")
	public R getSysDatasourceConfPage(Page page, SysDatasourceConf sysDatasourceConf) {
		return new R<>(sysDatasourceConfService.page(page, Wrappers.query(sysDatasourceConf)));
	}

	/**
	 * 查询全部数据源
	 *
	 * @return
	 */
	@GetMapping("/list")
	public R list() {
		return new R<>(sysDatasourceConfService.list());
	}


	/**
	 * 通过id查询数据源表
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Integer id) {
		return new R<>(sysDatasourceConfService.getById(id));
	}

	/**
	 * 新增数据源表
	 *
	 * @param sysDatasourceConf 数据源表
	 * @return R
	 */
	@SysLog("新增数据源表")
	@PostMapping
	public R save(@RequestBody SysDatasourceConf sysDatasourceConf) {
		sysDatasourceConfService.save(sysDatasourceConf);
		dynamicDataSourceConfig.reload();
		return new R<>();
	}

	/**
	 * 修改数据源表
	 *
	 * @param sysDatasourceConf 数据源表
	 * @return R
	 */
	@SysLog("修改数据源表")
	@PutMapping
	public R updateById(@RequestBody SysDatasourceConf sysDatasourceConf) {
		sysDatasourceConfService.updateById(sysDatasourceConf);
		dynamicDataSourceConfig.reload();
		return new R<>();
	}

	/**
	 * 通过id删除数据源表
	 *
	 * @param id id
	 * @return R
	 */
	@SysLog("删除数据源表")
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable Integer id) {
		sysDatasourceConfService.removeById(id);
		dynamicDataSourceConfig.reload();
		return new R<>();
	}

}

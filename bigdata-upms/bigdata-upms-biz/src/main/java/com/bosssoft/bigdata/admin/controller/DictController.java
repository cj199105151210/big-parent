package com.bosssoft.bigdata.admin.controller;


import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.admin.api.entity.SysDict;
import com.bosssoft.bigdata.admin.api.entity.SysDictItem;
import com.bosssoft.bigdata.admin.service.SysDictItemService;
import com.bosssoft.bigdata.admin.service.SysDictService;
import com.bosssoft.bigdata.common.core.util.R;
import com.bosssoft.bigdata.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典表 前端控制器
 *
 * @author lucky
 * @since 2019-03-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {
	private final SysDictService sysDictService;
	private final SysDictItemService sysDictItemService;

	/**
	 * 通过ID查询字典信息
	 *
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable Integer id) {
		return new R<>(sysDictService.getById(id));
	}

	/**
	 * 分页查询字典信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(Page page, SysDict sysDict) {
		return new R<>(sysDictService.page(page, Wrappers.query(sysDict)));
	}

	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = "dict_details", key = "#type")
	public R getDictByType(@PathVariable("type") String type) {
		return new R<>(sysDictItemService.list(Wrappers
				.<SysDictItem>query().lambda()
				.eq(SysDictItem::getType, type)));
	}

	/**
	 * 添加字典
	 *
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public R save(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.save(sysDict));
	}

	/**
	 * 删除字典，并且清除字典缓存
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	public R removeById(@PathVariable Integer id) {
		return new R<>(sysDictService.removeDict(id));
	}

	/**
	 * 修改字典
	 *
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R updateById(@Valid @RequestBody SysDict sysDict) {
		return new R<>(sysDictService.updateById(sysDict));
	}

	/**
	 * 分页查询
	 *
	 * @param page        分页对象
	 * @param sysDictItem 字典项
	 * @return
	 */
	@GetMapping("/item/page")
	public R getSysDictItemPage(Page page, SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.page(page, Wrappers.query(sysDictItem)));
	}


	/**
	 * 通过id查询字典项
	 *
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/{id}")
	public R getDictItemById(@PathVariable("id") Integer id) {
		return new R<>(sysDictItemService.getById(id));
	}

	/**
	 * 新增字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = "dict_details", allEntries = true)
	public R save(@RequestBody SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 *
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("修改字典项")
	@PutMapping("/item")
	@CacheEvict(value = "dict_details", allEntries = true)
	public R updateById(@RequestBody SysDictItem sysDictItem) {
		return new R<>(sysDictItemService.updateById(sysDictItem));
	}

	/**
	 * 通过id删除字典项
	 *
	 * @param id id
	 * @return R
	 */
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	@CacheEvict(value = "dict_details", allEntries = true)
	public R removeDictItemById(@PathVariable Integer id) {
		return new R<>(sysDictItemService.removeById(id));
	}
}

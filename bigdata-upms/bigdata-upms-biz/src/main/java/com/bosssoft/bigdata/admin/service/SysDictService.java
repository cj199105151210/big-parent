package com.bosssoft.bigdata.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.bigdata.admin.api.entity.SysDict;

/**
 * 字典表
 *
 * @author lucky
 * @date 2019-03-14
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据ID 删除字典
	 *
	 * @param id
	 * @return
	 */
	Boolean removeDict(Integer id);
}

package com.bosssoft.bigdata.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.entity.SysDict;
import com.bosssoft.bigdata.admin.api.entity.SysDictItem;
import com.bosssoft.bigdata.admin.mapper.SysDictItemMapper;
import com.bosssoft.bigdata.admin.mapper.SysDictMapper;
import com.bosssoft.bigdata.admin.service.SysDictService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典表
 *
 * @author lucky
 * @date 2019-03-14
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 *
	 * @param id
	 * @return
	 */
	@Override
	@CacheEvict(value = "dict_details", allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDict(Integer id) {
		baseMapper.deleteById(id);

		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery()
				.eq(SysDictItem::getDictId, id));
		return Boolean.TRUE;
	}
}

package com.bosssoft.bigdata.admin.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.bigdata.admin.api.dto.UserDTO;
import com.bosssoft.bigdata.admin.api.entity.SysUser;
import com.bosssoft.bigdata.admin.api.vo.UserVO;
import com.bosssoft.bigdata.common.data.datascope.DataScope;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lucky
 * @since 2017-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 *
	 * @param username 用户名
	 * @return userVo
	 */
	UserVO getUserVoByUsername(String username);

	/**
	 * 分页查询用户信息（含角色）
	 *
	 * @param page      分页
	 * @param userDTO   查询参数
	 * @param dataScope
	 * @return list
	 */
	IPage<List<UserVO>> getUserVosPage(Page page, @Param("query") UserDTO userDTO, DataScope dataScope);

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return userVo
	 */
	UserVO getUserVoById(Integer id);
}

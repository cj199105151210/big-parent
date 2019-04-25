package com.bosssoft.bigdata.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.bigdata.admin.api.dto.DeptTree;
import com.bosssoft.bigdata.admin.api.entity.SysDept;
import com.bosssoft.bigdata.admin.api.entity.SysDeptRelation;
import com.bosssoft.bigdata.admin.api.vo.TreeUtil;
import com.bosssoft.bigdata.admin.mapper.SysDeptMapper;
import com.bosssoft.bigdata.admin.service.SysDeptRelationService;
import com.bosssoft.bigdata.admin.service.SysDeptService;
import com.bosssoft.bigdata.common.core.constant.CommonConstants;
import com.bosssoft.bigdata.common.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author lucky
 * @since 2018-01-20
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 添加信息部门
	 *
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDept dept) {
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);
		return Boolean.TRUE;
	}


	/**
	 * 删除部门
	 *
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDeptById(Integer id) {
		//级联删除部门
		List<Integer> idList = sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda()
						.eq(SysDeptRelation::getAncestor, id))
				.stream()
				.map(SysDeptRelation::getDescendant)
				.collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			this.removeByIds(idList);
		}

		//删除部门级联关系
		sysDeptRelationService.deleteAllDeptRealtion(id);
		return Boolean.TRUE;
	}

	/**
	 * 更新部门
	 *
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDept sysDept) {
		//更新部门状态
		this.updateById(sysDept);
		//更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 *
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() {
		return getDeptTree(this.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 查询用户部门树
	 *
	 * @return
	 */
	@Override
	public List<DeptTree> getUserTree() {
		Integer deptId = SecurityUtils.getUser().getDeptId();
		List<Integer> descendantIdList = sysDeptRelationService
			.list(Wrappers.<SysDeptRelation>query().lambda()
				.eq(SysDeptRelation::getAncestor, deptId))
			.stream().map(SysDeptRelation::getDescendant)
			.collect(Collectors.toList());

		List<SysDept> deptList = baseMapper.selectBatchIds(descendantIdList);
		return getDeptTree(deptList);
	}

	/**
	 * 构建部门树
	 *
	 * @param depts 部门
	 * @return
	 */
	private List<DeptTree> getDeptTree(List<SysDept> depts) {
		List<DeptTree> treeList = depts.stream()
				.filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.build(treeList, CommonConstants.TREE_ROOT_ID);
	}
}

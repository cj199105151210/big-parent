package com.bosssoft.bigdata.admin.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lucky
 * @date 2019/2/18
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {
	private String name;
}

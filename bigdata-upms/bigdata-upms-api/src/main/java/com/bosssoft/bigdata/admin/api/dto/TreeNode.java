package com.bosssoft.bigdata.admin.api.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author lucky
 * @date 2019/2/18
 */
@Data
public class TreeNode {
	protected int id;
	protected int parentId;
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}
}

package com.bosssoft.bigdata.common.core.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树型节点类
 *
 * @author bigdata
 * @date 2019年03月21日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TreeNode extends BaseNode {

	private String title;

	private Integer key;

	private Integer value;

}

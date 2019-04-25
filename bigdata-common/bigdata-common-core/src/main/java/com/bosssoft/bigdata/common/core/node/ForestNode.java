package com.bosssoft.bigdata.common.core.node;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 森林节点类
 *
 * @author bigdata
 * @date 2019年03月21日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ForestNode extends BaseNode {

	/**
	 * 节点内容
	 */
	private Object content;

	public ForestNode(Integer id, Integer parentId, Object content) {
		this.id = id;
		this.parentId = parentId;
		this.content = content;
	}

}

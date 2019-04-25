package com.bosssoft.bigdata.common.core.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点基类
 *
 * @author bigdata
 * @date 2019年03月21日
 */
@Data
public class BaseNode implements INode {

	/**
	 * 主键ID
	 */
	protected Integer id;

	/**
	 * 父节点ID
	 */
	protected Integer parentId;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<INode> children = new ArrayList<>();

}

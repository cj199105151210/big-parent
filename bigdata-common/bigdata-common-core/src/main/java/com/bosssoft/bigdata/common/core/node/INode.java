package com.bosssoft.bigdata.common.core.node;

import java.util.List;

/**
 * @author bigdata
 * @date 2019年03月21日
 */
public interface INode {

	/**
	 * 主键
	 *
	 * @return Integer
	 */
	Integer getId();

	/**
	 * 父主键
	 *
	 * @return Integer
	 */
	Integer getParentId();

	/**
	 * 子孙节点
	 *
	 * @return List
	 */
	List<INode> getChildren();

}

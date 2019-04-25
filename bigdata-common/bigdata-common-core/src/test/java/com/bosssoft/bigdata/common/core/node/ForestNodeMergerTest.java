package com.bosssoft.bigdata.common.core.node;

import com.bosssoft.bigdata.common.core.jackson.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ForestNodeMergerTest {

    /*
        {
           "children" : [
              {
                 "content" : "节点4",
                 "id" : 4,
                 "parentId" : 2
              }
           ],
           "content" : "节点2",
           "id" : 2,
           "parentId" : 0
        }
    */
	@Test
	public void merge() {
		List<ForestNode> list = new ArrayList<>();
		list.add(new ForestNode(1, 0, "节点1"));
		list.add(new ForestNode(2, 0, "节点2"));
		list.add(new ForestNode(3, 1, "节点3"));
		list.add(new ForestNode(4, 2, "节点4"));
		list.add(new ForestNode(5, 3, "节点5"));
//		list.add(new ForestNode(6, 4, "节点6"));
//		list.add(new ForestNode(7, 3, "节点7"));
//		list.add(new ForestNode(8, 5, "节点8"));
//		list.add(new ForestNode(9, 6, "节点9"));
//		list.add(new ForestNode(10, 9, "节点10"));
		List<ForestNode> tns = ForestNodeMerger.merge(list);
		tns.forEach(node ->
				System.out.println(JsonUtil.toJson(node))
		);

	}
}
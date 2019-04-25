package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;

public class ObjectUtilTest {

	@Test
	public void isNotEmpty() {
		Object obj = new Object();
		System.out.println(ObjectUtil.isNotEmpty(obj));
		obj =null;
		System.out.println(ObjectUtil.isNotEmpty(obj));
	}
}
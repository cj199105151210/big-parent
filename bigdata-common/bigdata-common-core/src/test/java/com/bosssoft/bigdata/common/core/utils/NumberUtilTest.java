package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberUtilTest {

	@Test
	public void toInt() {
		System.out.println(NumberUtil.toInt("11111"));
	}

	/**
	 * 假如没有，默认填值
	 */
	@Test
	public void toInt1() {
		System.out.println(NumberUtil.toInt(null,99));
	}

	@Test
	public void toLong() {
	}

	@Test
	public void toLong1() {
	}

	@Test
	public void toDouble() {
	}

	@Test
	public void toDouble1() {
	}

	@Test
	public void toFloat() {
	}

	@Test
	public void toFloat1() {
	}

	@Test
	public void to62String() {
	}
}
package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class Base64UtilTest {

	@Test
	public void encode() {
		System.out.println(Base64Util.encode("hello"));
	}

	@Test
	public void encode1() {
		System.out.println(Base64Util.encode("hello",Charsets.UTF_8));
	}

	@Test
	public void decode() {
		System.out.println(Base64Util.decode("aGVsbG8="));
	}

}
package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;

import java.util.Date;

public class DateUtilTest {

	@Test
	public void setYears() {
		System.out.println(DateUtil.setYears(new Date(),1));
		System.out.println(DateUtil.formatDateTime(new Date()));
		System.out.println(DateUtil.formatDate(new Date()));
		System.out.println(DateUtil.formatTime(new Date()));
	}

}
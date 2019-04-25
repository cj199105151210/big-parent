package com.bosssoft.bigdata.daemon.quartz.task;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Lleyton J.
 * @date 2019-03-31
 */
@Slf4j
public class Person {
	private int age;
	private String name;

	public Person( String name,int age) {
		this.age = age;
		this.name = name;
	}

	public Person() {
	}
	//公有 有参方法
	public String public_str(String param)
	{
		log.info("测试于:{}，传入参数{}", LocalDateTime.now(), param);
		return "测试于:{}，传入参数{}" + param;
	}

	//公有 有参方法
	public void public_show(String str,int i)
	{
		System.out.println("public show "+str+"..."+i);
	}

	//公有 无参方法
	public void public_prin()
	{
		System.out.println("public prin");
	}


	//私有 有参方法
	private void private_show(String str,int i)
	{
		System.out.println("private show "+str+"..."+i);
	}

	//私有 无参方法
	private void private_prin()
	{
		System.out.println("private prin");
	}

}
package com.bosssoft.bigdata.common.core.utils;

import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @author: Lucky
 * @date: 2019/03/26
 * <p>
 * 字符集工具类
 */
public class Charsets {

	/**
	 * 字符集ISO-8859-1
	 */
	public static final java.nio.charset.Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
	/**
	 * 字符集GBK
	 */
	public static final java.nio.charset.Charset GBK = java.nio.charset.Charset.forName(StringPool.GBK);
	/**
	 * 字符集utf-8
	 */
	public static final java.nio.charset.Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * 转换为Charset对象
	 *
	 * @param charsetName 字符集，为空则返回默认字符集
	 * @return Charsets
	 * @throws UnsupportedCharsetException 编码不支持
	 */
	public static java.nio.charset.Charset charset(String charsetName) throws UnsupportedCharsetException {
		return StringUtil.isBlank(charsetName) ? java.nio.charset.Charset.defaultCharset() : java.nio.charset.Charset.forName(charsetName);
	}

}


/**
 * Copyright (C), 2019, 福建博思
 * FileName: ChineseToFirstLetterUtil
 * Author:   mapengpeng
 * Date:     2019/4/8 9:50
 * Description: 获取中文名称首字母工具类
 * version:  1.0
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bosssoft.bigdata.user.center.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChineseToFirstLetterUtil {
    public static String ChineseToFirstLetter(String c) {
        String string = "";
        char b;
        int a = c.length();
        for (int k = 0; k < a; k++) {
            b = c.charAt(k);
            String d = String.valueOf(b);
            String str = converterToFirstSpell(d);
            String s = str.toUpperCase();
            String g = s;
            char h;
            int j = g.length();
            for (int y = 0; y <= 0; y++) {
                h = g.charAt(0);
                string += h;
            }
        }
        return string;
    }

    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            String s = String.valueOf(nameChar[i]);
            if (s.matches("[\\u4e00-\\u9fa5]")) {
                try {
                    String[] mPinyinArray = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    pinyinName += mPinyinArray[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

}

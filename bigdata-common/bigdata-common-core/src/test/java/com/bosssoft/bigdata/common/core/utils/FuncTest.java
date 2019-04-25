package com.bosssoft.bigdata.common.core.utils;

import org.junit.Test;

public class FuncTest {

    @Test
    public void firstCharToLower() {
        System.out.println(Func.firstCharToLower("HELLO WORLD"));
    }

    @Test
    public void firstCharToUpper() {
        System.out.println(Func.firstCharToUpper("hello world"));
    }
}

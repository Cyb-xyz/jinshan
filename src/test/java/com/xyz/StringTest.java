package com.xyz;

import com.xyz.stringUtil.StringTool;

/**
 *  String工具类测试累
 */
public class StringTest {
    public static void main(String[] args) {
        String name = " a a ";
//        System.out.println(StringTool.isEmpty(name));
//        System.out.println(StringTool.isBlank(name));
        System.out.println(StringTool.trim(name));
        System.out.println(StringTool.trim("  "));
        System.out.println(StringTool.trimToNull("  "));
    }
}

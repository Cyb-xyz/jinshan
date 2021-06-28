package com.xyz.stringUtil;

/**
 *  字符串工具类
 */
public class StringTool {

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;

    /**
     *  判断字符串是否为null，或字符串长度等于0
     * @param str 需要判断的字符串
     * @return 当字符串为null或长度等于0时，返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public static boolean isNotEmpty(String str) {
        return !StringTool.isEmpty(str);
    }

    /**
     *  判断字符串是否是空白的
     * @param str 需要判断的字符串
     * @return 当字符串为null或字符串去除空格之后长度为0，返回true
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
    public static boolean isNotBlank(String str) {
        return !StringTool.isBlank(str);
    }


    /**
     *  去除字符串两边的空格
     * @param str 需要处理的字符串
     * @return 返回处理后的字符串
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     *  当字符串为一个空串时，则返回null
     * @param str 需要判断的字符串
     * @return  当字符串为"    "时，则返回null
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }
}


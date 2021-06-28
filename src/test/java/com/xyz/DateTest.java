package com.xyz;

import com.xyz.dateUtil.DateTool;

import java.util.Date;

/**
 *  测试日期工具类
 */
public class DateTest {
    public static void main(String[] args) {

//        Date date = new Date();
//        System.out.println(DateTool.formatDate1(date));
//        System.out.println(DateTool.formatDate2(date));
//        System.out.println(DateTool.formatDateTime(date));

//        String dateStr = "20210627";
//        System.out.println("日期1："+DateTool.parseDate1(dateStr));
//        System.out.println("日期2："+DateTool.parseDate2(dateStr));

        String dateStr = "2021-06-24";
        Date date = DateTool.parseDate1(dateStr);
        Date date1 = DateTool.addYears(date, 1);//增加年份
        System.out.println(DateTool.formatDate1(date1));

        date1 = DateTool.addMonths(date1, 2);   //增加月份
        System.out.println(DateTool.formatDate1(date1));

    }
}

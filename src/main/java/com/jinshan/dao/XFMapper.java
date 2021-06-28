package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-01 10:22
 */
@Mapper
public interface XFMapper {

    /**
     *  存储消防大屏--消防警情事件数据
     * @param paramMap
     * @return
     */
    int xxDp_xfJqSj(Map<String, Object> paramMap);







}

package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-05-19 9:00
 */
@Mapper
public interface StMapper {

    /**
     *  接收推送的商汤数据
     * @param paramMap 上传的数据
     * @return
     */
    int save(Map<String, Object> paramMap);

}

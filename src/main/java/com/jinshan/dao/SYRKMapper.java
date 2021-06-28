package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  实有人口
 * @Auther: 小宇宙
 * @Date: 2021-06-26 13:00
 */
@Mapper
public interface SYRKMapper {

    /**
     *  实有人口数据入库
     */
    int saveSYRK(Map<String, Object> paramMap);


}

package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  城市交通
 * @Auther: 小宇宙
 * @Date: 2021-06-26 14:30
 */
@Mapper
public interface CSJTMapper {

    /**
     *  停车场数据入库
     */
    int saveTCC(Map<String, Object> paramMap);


}

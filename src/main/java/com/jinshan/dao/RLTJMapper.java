package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-13 17:09
 */
@Mapper
public interface RLTJMapper {

    /**
     *  存储区域人流接口数据
     * @param paramMap
     * @return
     */
    int qyrl(Map<String, Object> paramMap);

    /**
     *  存储区域人群驻留时长接口数据
     * @param paramMap
     * @return
     */
    int zlsc(Map<String, Object> paramMap);

    /**
     *  存储区域人流画像基本信息接口数据
     * @param paramMap
     * @return
     */
    int rlhxjbxx(Map<String, Object> paramMap);


    /**
     *  行政区全局热力（订阅-热力图）接口数据
     * @param paramMap
     * @return
     */
    int xzqqjrl(Map<String, Object> paramMap);
}

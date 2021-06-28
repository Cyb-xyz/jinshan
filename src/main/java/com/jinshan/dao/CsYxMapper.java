package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  城市运行数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-05-26 10:39
 */
@Mapper
public interface CsYxMapper {
    /**
     *  存储考核断面数据
     * @param paramMap
     * @return
     */
    int khDmSave(Map<String, Object> paramMap);

    /**
     *  存储实有人口数据
     * @param paramMap
     * @return
     */
    int syRkSave(Map<String, Object> paramMap);

    /**
     *  存储城市交通数据
     * @param paramMap
     * @return
     */
    int csJtSave(Map<String, Object> paramMap);

    /**
     * 存储城市环境大气数据
     * @param paramMap
     * @return
     */
    int cshJdqData(Map<String, Object> paramMap);

    /**
     *  删除城市环境大气数据旧的数据
     * @return
     */
    int deleteDqData();
}

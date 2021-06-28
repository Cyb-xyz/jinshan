package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-01 10:22
 */
@Mapper
public interface BaiDuMapper {
    /**
     *  重点道路路段排行数据入库
     * @param paramMap 需要存入的数据
     * @return
     */
    int zDdlLdPhSave(Map<String, Object> paramMap);

    /**
     *  重点道路道路排行数据入库
     * @param paramMap
     * @return
     */
    int zddl_dlPhSave(Map<String, Object> paramMap);

    /**
     *  重点道路信息数据入库
     * @param paramMap
     * @return
     */
    int zddlXxSave(Map<String, Object> paramMap);

    /**
     *  重点道路道路拥堵指标详情数据入库
     * @param paramMap
     * @return
     */
    int zddlDlYdZbXq(Map<String, Object> paramMap);

    /**
     *  重点道路路段拥堵指标详情数据入库
     * @param paramMap
     * @return
     */
    int zddlLdYdZbXq(Map<String, Object> paramMap);


    /**
     *  百度——路段拥堵指标详情入库   新的。
     */
    int saveLDYDZBXQ(Map<String, Object> paramMap);

    /**
     *  从重点道路信息表中查询所有路段的id
     */
    List<String> selectLDID();

}

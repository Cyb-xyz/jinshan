package com.jinshan.service;

import cn.hutool.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 *  百度接口服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-01 9:43
 */
public interface BaiDuAPIService {
    /**
     *  重点道路路段排行数据入库
     */
    void zDdlLdPhSave(JSONObject result);

    /**
     *  重点道路道路排行数据入库
     */
    void zddlDlPhSave(JSONObject result);

    /**
     *  重点道路信息数据入库
     */
    void zddlXxSave(JSONObject result);

    /**
     *  重点道路道路拥堵指标详情数据入库
     */
    void zddlDlYdZbXqSave(JSONObject result);

    /**
     *  重点道路路段拥堵指标详情数据入库
     */
    void zddlLdYdZbXqSave(JSONObject result);

    /**
     *  百度——路段拥堵指标详情入库   新的。
     */
    int saveLDYDZBXQ(JSONObject result, Date date);

    /**
     *  从重点道路信息表中查询所有路段的id
     */
    List<String> selectLDID();
}

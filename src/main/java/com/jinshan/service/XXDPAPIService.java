package com.jinshan.service;

import cn.hutool.json.JSONObject;

/**
 *  消防服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-01 9:43
 */
public interface XXDPAPIService {
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
}

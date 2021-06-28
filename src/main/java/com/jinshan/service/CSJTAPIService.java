package com.jinshan.service;

import cn.hutool.json.JSONArray;

/**
 * 城市交通入库服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-26 14:41
 */
public interface CSJTAPIService {
    /**
     *  停车场数据入库
     */
    int saveTCC(JSONArray jsonArray);
}

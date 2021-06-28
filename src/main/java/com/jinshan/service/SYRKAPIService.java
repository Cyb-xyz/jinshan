package com.jinshan.service;

/**
 * 实有人口入库服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-26 13:00
 */
public interface SYRKAPIService {
    /**
     *  实有人口数据入库
     */
    int saveSYRK(String jsonStr);
}

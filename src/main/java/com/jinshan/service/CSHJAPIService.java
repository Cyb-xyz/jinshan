package com.jinshan.service;

/**
 *  城市环境指标服务层接口
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:33
 */
public interface CSHJAPIService {
    /**
     *  空气质量API实时数据入库
     */
    int saveKQZL(String jsonStr);


    /**
     *  城市环境考核断面数据入库
     */
    int saveKHDM(String jsonStr);

}

package com.jinshan.service;

/**
 *  高德服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-18 10:58
 */
public interface GaoDeService {
    /**
     *  高德_交通路况信息入库
     * @param jsonStr   数据信息
     * @return
     */
    int saveJtLkXX(String jsonStr);


    /**
     *  高德_交通路况道路ID信息入库
     * @param jsonStr 数据信息
     * @return
     */
    int saveJtLkIDXX(String jsonStr);
}

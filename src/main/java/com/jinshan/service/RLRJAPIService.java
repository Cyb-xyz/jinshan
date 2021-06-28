package com.jinshan.service;


/**
 *  人流统计服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:02:35
 */
public interface RLRJAPIService {
    /**
     *  存储区域人流接口数据
     */
    void qyrlSave(String result);

    /**
     *  存储区域人群驻留时长接口数据
     */
    void zlscSave(String result);

    /**
     *  存储区域人流画像基本信息接口数据
     */
    void rlhxjbxxSave(String result);

    /**
     *  行政区全局热力（订阅-热力图）接口数据
     */
    void xzqqjrlSave(String result);
}

package com.jinshan.service;


/**
 *  危化指标服务层
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:02:35
 */
public interface WeiHuaAPIService {
    /**
     *  企业信息总览信息入库
     * @param result    第三方接口返回的json字符串
     * @return
     */
    int save_qyxxzl(String result);


    /**
     *  危险化学品总览数据入库
     * @param result    第三方接口返回的json字符串
     * @return
     */
    int saveWXHXPZL(String result);

    /**
     *  危化车辆，前日累计（辆）数据入库
     * @param result 第三方接口返回的json字符串
     * @return
     */
    int saveQRLJCL(String result);

    /**
     *  危化车辆，实时数量（辆）数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    int saveSSCSL(String jsonStr);


    /**
     *  危险船舶数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    int saveWXCB(String jsonStr);


    /**
     *  地下化工管线数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    int saveDXHGGX(String jsonStr);


    /**
     *  监管动态总览数据入库
     * @param jsonStr 第三方接口返回的json字符串
     * @return
     */
    int saveJGDTZL(String jsonStr);
}

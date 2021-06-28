package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  危化指标数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-06-17 8:45
 */
@Mapper
public interface WeiHuaMapper {

    /**
     *  企业信息总览信息入库
     * @param paramMap
     * @return
     */
    int save_qyxxzl(Map<String, Object> paramMap);

    /**
     *  危险化学品总览数据入库
     * @param paramMap
     * @return
     */
    int saveWXHXPZL(Map<String, Object> paramMap);


    /**
     *  危化车辆，前日累计（辆）数据入库
     * @param paramMap
     * @return
     */
    int saveQRLJCL(Map<String, Object> paramMap);



    /**
     *  危化车辆，实时数量（辆）数据入库
     * @param paramMap
     * @return
     */
    int saveSSCSL(Map<String, Object> paramMap);


    /**
     *  危险船舶数据入库
     * @param paramMap
     * @return
     */
    int saveWXCB(Map<String, Object> paramMap);


    /**
     *  地下化工管线数据入库
     * @param paramMap
     * @return
     */
    int saveDXHGGX(Map<String, Object> paramMap);


    /**
     *  监管动态总览数据入库
     * @param paramMap
     * @return
     */
    int saveJGDTZL(Map<String, Object> paramMap);







}

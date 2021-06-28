package com.jinshan.service;

import java.util.List;
import java.util.Map;

/**
 *  区网格化中心指标服务层接口
 * @Auther: 小宇宙
 * @Date:  2021-06-23 18:00
 */
public interface WGHZXAPIService {
    /**
     *  新冠疫苗接种统计-街镇(疫苗类型)入库
     */
    int saveXGYMJZTJ(String jsonStr);


    /**
     *  新冠疫苗接种统计-街镇入库
     */
    int saveXGYMJZTJ_JZ(String jsonStr);

    /**
     *  区人口-分年龄段统计全区数据入库
     */
    int saveQRKNLDTJ_QQ(String jsonStr);

    /**
     *  区人口-分年龄段统计街镇数据入库
     */
    int saveQRKNLDTJ_JZ(String jsonStr);


    /**
     *  获取区人口-分年龄段统计（街镇）数据
     */
    List<Map<String, String>> getQRKNLDTJ_JZ();


    /**
     *  根据街道名称获取街镇最新的人员分段数据
     */
    List<Map<String, String>> getJZRKFD(String name);







}

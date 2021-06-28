package com.jinshan.dao;

import com.jinshan.pojo.RenKouFD;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  区网格化中心数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-06-23 18:00
 */
@Mapper
public interface WGHZXMapper {

    /**
     *  新冠疫苗接种统计-街镇(疫苗类型)入库
     * @param paramMap 接口的数据
     * @return
     */
    int saveXGYMJZTJ(Map<String, Object> paramMap);


    /**
     *  新冠疫苗接种统计-街镇
     * @param paramMap 接口的数据
     * @return
     */
    int saveXGYMJZTJ_JZ(Map<String, Object> paramMap);


    /**
     *  区人口-分年龄段统计数据入库(全区和街镇)
     */
    int saveQRKNLDTJ(Map<String, Object> paramMap);

    /**
     *  获取各个街道名称
     */
    List<String> getNewREGIONNAME();

    /**
     *  根据街道名称获取街镇最新的人员分段数据
     */
    List<RenKouFD> getJZRYFDSJ(String name);

    /**
     *  根据街道名称获取街镇最新的人员分段数据
     */
    List<Map<String, String>> getJZRKFD(String name);

}

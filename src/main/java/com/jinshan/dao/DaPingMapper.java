package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  大屏指标模块数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-06-19 12:47
 */
@Mapper
public interface DaPingMapper {

    /**
     *  大屏-小微站-模块（小微站模块数据）
     * @param map
     * @return
     */
    int saveXWZ(Map<String, Object> map);

    /**
     *  大屏-扬尘污染-模块（扬尘污染模块）
     * @param map
     * @return
     */
    int saveYCWR(Map<String, Object> map);

    /**
     *  大屏-重点排污单位-模块（重点排污模块数据）
     * @param map
     * @return
     */
    int saveZDPWDW(Map<String, Object> map);

    /*****************获取最新小微站数据*******************/
    // 获取小微站最新Echart雷达图数据
    List<Map<String, String>> selectLDTSJ();
    //获取小微站最新 超标报警数, 定位数, 污染溯源数 数据
    List<Map<String, String>> selectOther();


    /*****************获取最新重点排污单位数据*******************/
    // 获取小重点排污单位总和数据
    List<Map<String, String>> selectPwSUM();
    // 获取小重点排污单位数据列表
    List<Map<String, String>> selectPwList();






}

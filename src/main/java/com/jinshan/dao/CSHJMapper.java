package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  城市环境指标数据库交互层
 * @Auther: 小宇宙
 * @Date: 2021-06-23 9:09
 */
@Mapper
public interface CSHJMapper {

    /***************空气质量API实时数据入库**************/

    //空气质量预报入库
    int saveKQZLYB(Map<String, Object> paramMap);
    //当日空气质量信息入库
    int saveDRKQZLXX(Map<String, Object> paramMap);
    //地区空气质量信息入库
    int saveDQKQZLXX(Map<String, Object> paramMap);

    /************城市环境考核断面数据入库**************/

    //考核断面达标情况入库
    int saveKHDMDBQK(Map<String, Object> paramMap);
    //全区地表水水质类别入库
    int saveQKDBSSZLB(Map<String, Object> paramMap);



}

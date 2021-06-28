package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *  城市预警数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-05-19 9:00
 */
@Mapper
public interface CsYjMapper {

    /**
     *  城市预警-公安数据入库
     * @param paramMap 上传的数据
     * @return
     */
    int saveGA(Map<String, Object> paramMap);

    /**
     *  城市预警--消防警情数据入库
     * @return
     */
    int saveXFJQ(Map<String, Object> paramMap);

}

package com.jinshan.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  高德数据交互层
 * @Auther: 小宇宙
 * @Date: 2021-06-17 8:45
 */
@Mapper
public interface GaoDeMapper {
    /**
     *  高德_交通路况信息入库
     * @param map
     * @return
     */
    int saveJtLkXX(Map<String, Object> map);

    /**
     *  高德_交通路况道路ID信息入库
     * @return
     */
    int saveJtLkIDXX(Map<String, Object> map);
}

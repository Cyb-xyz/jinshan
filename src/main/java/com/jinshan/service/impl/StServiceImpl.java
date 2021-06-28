package com.jinshan.service.impl;

import com.jinshan.dao.StMapper;
import com.jinshan.service.StService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *  商汤业务接口实现类
 * @Auther: 小宇宙
 * @Date: 2021-05-19 8:58
 */
@Service
public class StServiceImpl implements StService {

    @Autowired
    private StMapper stMapper;

    /**
     *  接收推送的商汤数据
     * @param paramMap  需要上传的数据
     * @return
     */
    @Override
    public int save(Map<String, Object> paramMap) {
        return stMapper.save(paramMap);
    }




}

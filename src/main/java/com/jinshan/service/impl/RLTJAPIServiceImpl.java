package com.jinshan.service.impl;

import com.jinshan.dao.RLTJMapper;
import com.jinshan.service.RLRJAPIService;
import com.jinshan.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-13 17:15
 */
@Service
public class RLTJAPIServiceImpl implements RLRJAPIService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RLTJMapper rltjMapper;

    /**
     *  存储区域人流接口数据
     * @param result 接口数据
     */
    @Override
    public void qyrlSave(String result) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");
        paramMap.put("data", result);
        paramMap.put("data_time", new Date());
        try {
            int num = rltjMapper.qyrl(paramMap);
            System.out.println("区域人流接口数据入库条数："+num);
        } catch (Exception e) {
            System.out.println("区域人流接口数据入库出现异常");
            e.printStackTrace();
        }

    }

    /**
     *  存储区域人群驻留时长接口数据
     * @param result 接口数据
     */
    @Override
    public void zlscSave(String result) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");
        paramMap.put("data", result);
        paramMap.put("data_time", new Date());
        try {
            int num = rltjMapper.zlsc(paramMap);
            System.out.println("区域人群驻留时长接口数据入库条数："+num);
        } catch (Exception e) {
            System.out.println("区域人群驻留时长接口数据入库出现异常");
            e.printStackTrace();
        }
    }

    /**
     *  存储区域人流画像基本信息接口数据
     * @param result 接口数据
     */
    @Override
    public void rlhxjbxxSave(String result) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");
        paramMap.put("data", result);
        paramMap.put("data_time", new Date());
        try {
            int num = rltjMapper.rlhxjbxx(paramMap);
            System.out.println("区域人流画像基本信息接口数据入库条数："+num);
        } catch (Exception e) {
            System.out.println("区域人流画像基本信息接口数据入库出现异常");
            e.printStackTrace();
        }
    }

    /**
     *  行政区全局热力（订阅-热力图）接口数据
     * @param result 接口数据
     */
    @Override
    public void xzqqjrlSave(String result) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", idWorker.nextId()+"");
        paramMap.put("data", result);
        paramMap.put("data_time", new Date());
        try {
            int num = rltjMapper.xzqqjrl(paramMap);
            System.out.println("行政区全局热力接口数据入库条数："+num);
        } catch (Exception e) {
            System.out.println("行政区全局热力接口数据入库出现异常");
            e.printStackTrace();
        }
    }
}

package com.jinshan.service.impl;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.dao.XFMapper;
import com.jinshan.service.XFAPIService;
import com.jinshan.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:06
 */
@Service
public class XFServiceImpl implements XFAPIService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private XFMapper xfMapper;

    /**
     *  消防警情事件数据入库
     */
    @Override
    public void xfJqSjSave(String result) {
        //解析json字符串
        JSONObject jsonObject = JSONObject.parseObject(result);
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray jsonArray = data.getJSONArray("records");
            for (Object object : jsonArray) {
                Map<String, Object> paramMap = new HashMap<>();

                paramMap.put("id", idWorker.nextId()+"");
                paramMap.put("recordsData", object.toString());
                paramMap.put("datatime", new Date());
                try {
                    int success = xfMapper.xxDp_xfJqSj(paramMap);
                    System.out.println("消防警情事件数据入库:"+success);
                } catch (Exception e) {
                    System.out.println("消防警情事件数据入库出现异常"+e.getMessage());
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


















}

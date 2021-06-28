package com.jinshan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.dao.GaoDeMapper;
import com.jinshan.service.GaoDeService;
import com.jinshan.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *  高德服务层实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-18 11:00
 */
@Service
public class GaoDeServiceImpl implements GaoDeService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private GaoDeMapper gaoDeMapper;

    /**
     *  高德_交通路况信息入库
     * @param jsonStr   数据信息
     * @return
     */
    @Override
    public int saveJtLkXX(String jsonStr) {

        System.out.println("高德_交通路况信息入库");

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        String name = jsonObject.getString("name");
        String utcSeconds = jsonObject.getString("utcSeconds");
        String autolrDataVersion = jsonObject.getString("autolrDataVersion");
        Date date = new Date();
        int number = 0;
        JSONArray jsonArray = jsonObject.getJSONArray("linkStates");
        for (int i = 0; i < jsonArray.size(); i++) {
            String data = jsonArray.get(i).toString();
            JSONObject object = JSONObject.parseObject(data);
            Map<String, Object> map = new HashMap<>();
            map.put("ID", idWorker.nextId() + "");

            map.put("NAME", name);
            map.put("UTCSECONDS", utcSeconds);
            map.put("AUTOLRDATAVERSION", autolrDataVersion);

            map.put("LINKID", object.getString("linkId"));
            map.put("TRAVELTIME", object.getString("travelTime"));
            map.put("STATE", object.getString("state"));
            map.put("SPEED", object.getString("speed"));

            map.put("DATA_TIME", date);
            number += gaoDeMapper.saveJtLkXX(map);
        }

        return number;
    }

    /**
     *  高德_交通路况道路ID信息入库
     * @param jsonStr 数据信息
     * @return
     */
    @Override
    public int saveJtLkIDXX(String jsonStr) {

        System.out.println("高德_交通路况道路ID信息入库");

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        String name = jsonObject.getString("name");
        String utcSeconds = jsonObject.getString("utcSeconds");
        Date date = new Date(); //数据入库时间
        int number = 0;
        //JSON数组
        JSONArray jsonArray = jsonObject.getJSONArray("linkCoordList");
        for (int i = 0; i < jsonArray.size(); i++) {

            String data = jsonArray.get(i).toString();
            JSONObject object = JSONObject.parseObject(data);
            Map<String, Object> map = new HashMap<>();

            map.put("ID", idWorker.nextId() + "");

            map.put("COORDLIST", object.getString("coordList"));
            map.put("FORMWAY", object.getString("formway"));
            map.put("LENGTH", object.getString("length"));
            map.put("LINKID", object.getString("linkId"));
            map.put("ROADNAME", object.getString("roadName"));
            map.put("ROADCLASS", object.getString("roadclass"));

            map.put("NAME", name);
            map.put("UTCSECONDS", utcSeconds);
            map.put("DATA_TIME", date);
            number += gaoDeMapper.saveJtLkIDXX(map);
        }

        return number;
    }


}

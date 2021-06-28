package com.jinshan.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.jinshan.dao.CSJTMapper;
import com.jinshan.service.CSJTAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  城市交通入库服务层实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-26 13:01
 */
@Service
public class CSJTAPIServiceImpl implements CSJTAPIService {

    @Autowired
    private CSJTMapper csjtMapper;

    @Autowired
    private IdWorker idWorker;

    private static Logger logger = LoggerFactory.getLogger(CSJTAPIServiceImpl.class);


    /**
     *  停车场数据入库
     */
    @Override
    public int saveTCC(JSONArray jsonArray) {
        logger.info("进入停车场数据入库服务层");
        int number = 0;
        Date date = new Date(); //数据入库时间
        for (int i=0; i<jsonArray.size(); i++) {

            JSONObject object = jsonArray.getJSONObject(i);

            String id = object.getStr("id") == null ? "" : object.getStr("id");
            String parkId = object.getStr("parkId") == null ? "" : object.getStr("parkId");
            String name = object.getStr("name") == null ? "" : object.getStr("name");
            String address = object.getStr("address") == null ? "" : object.getStr("address");
            String lng = object.getStr("lng") == null ? "" : object.getStr("lng");
            String lat = object.getStr("lat") == null ? "" : object.getStr("lat");
            String type = object.getStr("type") == null ? "" : object.getStr("type");
            String company = object.getStr("company") == null ? "" : object.getStr("company");
            String manager = object.getStr("manager") == null ? "" : object.getStr("manager");
            String mobile = object.getStr("mobile") == null ? "" : object.getStr("mobile");
            String spaceSum = object.getStr("spaceSum") == null ? "" : object.getStr("spaceSum");
            String spaceLeft = object.getStr("spaceLeft") == null ? "" : object.getStr("spaceLeft");
            String setValue = object.getStr("setValue") == null ? "" : object.getStr("setValue");
            String mode = object.getStr("mode") == null ? "" : object.getStr("mode");
            String updateTime = object.getStr("updateTime") == null ? "" : object.getStr("updateTime");
            String deviceUpdateTime = object.getStr("deviceUpdateTime") == null ? "" : object.getStr("deviceUpdateTime");
            String deviceSendTime = object.getStr("deviceSendTime") == null ? "" : object.getStr("deviceSendTime");
            String district = object.getStr("district") == null ? "" : object.getStr("district");

            Map<String, Object> map = new HashMap<>();
            map.put("ID", idWorker.nextId()+"");
            map.put("DATA_ID", id);
            map.put("PARKID", parkId);
            map.put("NAME", name);
            map.put("ADDRESS", address);
            map.put("LNG", lng);
            map.put("LAT", lat);
            map.put("TYPE", type);
            map.put("COMPANY", company);
            map.put("MANAGER", manager);
            map.put("MOBILE", mobile);
            map.put("SPACESUM", spaceSum);
            map.put("SPACELEFT", spaceLeft);
            map.put("SETVALUE", setValue);
            map.put("MODE", mode);
            map.put("UPDATETIME", updateTime);
            map.put("DEVICEUPDATETIME", deviceUpdateTime);
            map.put("DEVICESENDTIME", deviceSendTime);
            map.put("DISTRICT", district);
            map.put("CREATE_TIME", date);

            number += csjtMapper.saveTCC(map);
        }

        return number;
    }












}

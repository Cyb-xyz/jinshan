package com.jinshan.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.dao.CSHJMapper;
import com.jinshan.service.CSHJAPIService;
import com.jinshan.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 城市环境指标服务层接口实现类
 * @Auther: 小宇宙
 * @Date: 2021-06-23 9:16
 */
@Service
public class CSHJAPIServiceImpl implements CSHJAPIService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CSHJMapper cshjMapper;

    private static Logger logger = LoggerFactory.getLogger(CSHJAPIServiceImpl.class);

    /**
     *  空气质量API实时数据入库
     */
    @Override
    public int saveKQZL(String jsonStr) {
        logger.info("进入空气质量API实时数据入库服务层");
        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            Date date = new Date();
            if (data != null) {

                //当日空气质量信息入库
                String aqiDQRate = data.getString("aqiDQRate") == null ? "" : data.getString("aqiDQRate");
                String aqiMBRate = data.getString("aqiMBRate") == null ? "" : data.getString("aqiMBRate");
                String aqiDQDay = data.getString("aqiDQDay") == null ? "" : data.getString("aqiDQDay");
                String aqiMBDay = data.getString("aqiMBDay") == null ? "" : data.getString("aqiMBDay");
                String aqiVal = data.getString("aqiVal") == null ? "" : data.getString("aqiVal");
                String aqiStatus = data.getString("aqiStatus") == null ? "" : data.getString("aqiStatus");
                String aqiColor = data.getString("aqiColor") == null ? "" : data.getString("aqiColor");
                String aqiPic = data.getString("aqiPic") == null ? "" : data.getString("aqiPic");

                Map<String, Object> map1 = new HashMap<>();
                map1.put("ID", idWorker.nextId()+"");
                map1.put("AQIVAL", aqiVal);
                map1.put("AQISTATUS", aqiStatus);
                map1.put("AQICOLOR", aqiColor);
                map1.put("AQIMBDAY", aqiMBDay);
                map1.put("AQIDQDAY", aqiDQDay);
                map1.put("AQIMBRATE", aqiMBRate);
                map1.put("AQIDQRATE", aqiDQRate);
                map1.put("AQIPIC", aqiPic);
                map1.put("CREATE_TIME", date);

                int num1 = cshjMapper.saveDRKQZLXX(map1);//当日空气质量信息入库
                number += num1;
                logger.info("当日空气质量信息入库:{}条", num1);

                //城市环境_空气质量预报入库
                JSONArray ybList = data.getJSONArray("ybList");
                for (int i=0; i<ybList.size(); i++) {
                    JSONObject object = ybList.getJSONObject(i);
                    String seg = object.getString("seg") == null ? "" : object.getString("seg");
                    String param = object.getString("param") == null ? "" : object.getString("param");
                    String grade = object.getString("grade") == null ? "" : object.getString("grade");

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("ID", idWorker.nextId()+"");
                    map2.put("SEG", seg);
                    map2.put("PARAM", param);
                    map2.put("GRADE", grade);
                    map2.put("CREATE_TIME", date);

                    int num2 = cshjMapper.saveKQZLYB(map2);//空气质量预报入库
                    number += num2;
                    logger.info("空气质量预报入库:{}条", num2);
                }

                //城市环境_地区空气质量信息入库
                JSONArray zdList = data.getJSONArray("zdList");
                for (int i=0; i<zdList.size(); i++) {
                    JSONObject object = zdList.getJSONObject(i);

                    String val = object.getString("val") == null ? "" : object.getString("val");
                    String color = object.getString("color") == null ? "" : object.getString("color");
                    String x = object.getString("x") == null ? "" : object.getString("x");
                    String name = object.getString("name") == null ? "" : object.getString("name");
                    String y = object.getString("y") == null ? "" : object.getString("y");
                    String pj = object.getString("pj") == null ? "" : object.getString("pj");
                    String id = object.getString("id") == null ? "" : object.getString("id");
                    String wrw = object.getString("wrw") == null ? "" : object.getString("wrw");

                    Map<String, Object> map3 = new HashMap<>();
                    map3.put("ID", idWorker.nextId()+"");
                    map3.put("DATA_ID", id);
                    map3.put("NAME", name);
                    map3.put("VAL", val);
                    map3.put("PJ", pj);
                    map3.put("WRW", wrw);
                    map3.put("X", x);
                    map3.put("Y", y);
                    map3.put("COLOR", color);
                    map3.put("CREATE_TIME", date);

                    int num3 = cshjMapper.saveDQKQZLXX(map3);//地区空气质量信息入库
                    number += num3;
                    logger.info("地区空气质量信息入库:{}条", num3);
                }

            }
        }

        return number;
    }


    /**
     *  城市环境考核断面数据入库
     */
    @Override
    public int saveKHDM(String jsonStr) {
        logger.info("进入城市环境考核断面数据入库服务层");
        int number = 0;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            Date date = new Date(); //入库时间
            if (data != null) {
                /******************获取考核断面达标情况数据********************/
                String lyNum = data.getString("lyNum") == null ? "" : data.getString("lyNum");
                String skdbRate = data.getString("skdbRate") == null ? "" : data.getString("skdbRate");
                String gkdbRate = data.getString("gkdbRate") == null ? "" : data.getString("gkdbRate");
                String yyNum = data.getString("yyNum") == null ? "" : data.getString("yyNum");
                String skNum = data.getString("skNum") == null ? "" : data.getString("skNum");
                String crjdbRate = data.getString("crjdbRate") == null ? "" : data.getString("crjdbRate");
                String crjNum = data.getString("crjNum") == null ? "" : data.getString("crjNum");
                String dbbmNum = data.getString("dbbmNum") == null ? "" : data.getString("dbbmNum");
                String dbRate = data.getString("dbRate") == null ? "" : data.getString("dbRate");
                String gkNum = data.getString("gkNum") == null ? "" : data.getString("gkNum");
                String pyNum = data.getString("pyNum") == null ? "" : data.getString("pyNum");

                Map<String, Object> map1 = new HashMap<>();
                map1.put("ID", idWorker.nextId()+"");
                map1.put("LYNUM", lyNum);
                map1.put("SKDBRATE", skdbRate);
                map1.put("GKDBRATE", gkdbRate);
                map1.put("YYNUM", yyNum);
                map1.put("SKNUM", skNum);
                map1.put("CRJDBRATE", crjdbRate);
                map1.put("CRJNUM", crjNum);
                map1.put("DBBMNUM", dbbmNum);
                map1.put("DBRATE", dbRate);
                map1.put("GKNUM", gkNum);
                map1.put("PYNUM", pyNum);
                map1.put("CREATE_TIME", date);

                int num1 = cshjMapper.saveKHDMDBQK(map1);
                number += num1;
                logger.info("考核断面达标情况入库:{}条", num1);


                /******************全区地表水水质类别入库********************/
                JSONArray szTypes = data.getJSONArray("szTypes");
                for (int i=0; i<szTypes.size(); i++) {
                    JSONObject object = szTypes.getJSONObject(i);

                    String rateYear = object.getString("rateYear") == null ? "" : object.getString("rateYear");
                    String average = object.getString("average") == null ? "" : object.getString("average");
                    String total = object.getString("total") == null ? "" : object.getString("total");
                    String averageName = object.getString("averageName") == null ? "" : object.getString("averageName");
                    String rateUpdown = object.getString("rateUpdown") == null ? "" : object.getString("rateUpdown");
                    String averageColor = object.getString("averageColor") == null ? "" : object.getString("averageColor");
                    String id = object.getString("id") == null ? "" : object.getString("id");
                    String rateZb = object.getString("rateZb") == null ? "" : object.getString("rateZb");
                    String isShow = object.getString("isShow") == null ? "" : object.getString("isShow");

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("ID", idWorker.nextId()+"");
                    map2.put("DATA_ID", id);
                    map2.put("AVERAGENAME", averageName);
                    map2.put("TOTAL", total);
                    map2.put("RATEZB", rateZb);
                    map2.put("RATEYEAR", rateYear);
                    map2.put("AVERAGE", average);
                    map2.put("RATEUPDOWN", rateUpdown);
                    map2.put("ISSHOW", isShow);
                    map2.put("AVERAGECOLOR", averageColor);
                    map2.put("CREATE_TIME", date);

                    int num2 = cshjMapper.saveQKDBSSZLB(map2);
                    number += num2;
                    logger.info("全区地表水水质类别入库:{}条", num2);
                }

            }

        }

        return number;
    }

































}

package com.jinshan.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.dao.CsYjMapper;
import com.jinshan.service.CsYjAPIService;
import com.jinshan.util.DateUtils;
import com.jinshan.util.IdWorker;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  城市预警服务层接口实现类
 * @Auther: 小宇宙
 * @Date: 2021-05-25 13:38
 */
@Service
public class CsYjApiServiceImpl implements CsYjAPIService {

    @Autowired
    private CsYjMapper csYjMapper;

    @Autowired
    private IdWorker idWorker;

    private static Logger logger = LoggerFactory.getLogger(CsYjApiServiceImpl.class);

    /**
     *  从远程接口获取城市预警公安数据，并将数据存储。
     *  城市预警-公安数据入库
     */
    @Override
    public int saveCsYjGa() {
        logger.info("进入城市预警-公安数据入库服务层实现类");
        try {
            //请求远程接口
            String json = HttpUtil.get(JinShanAPIEnum.getCsYjGaData.getUrl());
            //解析获取到的json字符串
            JSONObject jsonObject = JSONUtil.parseObj(json);
            if (jsonObject != null) {
                JSONObject data = jsonObject.getJSONObject("data");
                if (data != null) {

                    JSONArray causeActionNum = data.getJSONArray("causeActionNum");

                    int success = 0;  //入库数量
                    Date date = new Date();  //入库时间

                    for (int i=0; i<causeActionNum.size(); i++) {
                        JSONObject object = causeActionNum.getJSONObject(i);

                        String alarm_type = object.getStr("alarm_type") == null ? "" : object.getStr("alarm_type");    //警报类型
                        String afcs = object.getStr("afcs") == null ? "" : object.getStr("afcs");    //警报数量

                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("ID", idWorker.nextId()+"");
                        paramMap.put("ALARM_TYPE", alarm_type);
                        paramMap.put("AFCS", afcs);
                        paramMap.put("DATA_TIME", date);

                        logger.info("城市预警-公安数据入库");
                        success += csYjMapper.saveGA(paramMap);
                    }
                    return success;
                }
            }
        } catch (Exception e) {
            logger.info("城市预警-公安数据入库出现异常,{}", e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *  城市预警--消防警情数据入库
     */
    @Override
    public int saveXFJQ() {
        logger.info("进入城市预警--消防警情数据入库服务层实现类");

        //拼接请求参数
        StringBuffer param = new StringBuffer();

        param.append("&page=" + 1);  //第一页
        param.append("&limit=" + 500);  //页面条数
        param.append("&startDate=" + DateUtils.getDate());  //当前时间

        try {
            String json = HttpUtils.sendGet(JinShanAPIEnum.getXFJQSj.getUrl(), param.toString());
            JSONObject jsonObject = JSONUtil.parseObj(json);
            if (jsonObject != null) {
                JSONObject data = jsonObject.getJSONObject("data");
                if (data != null) {
                    JSONArray jsonArray = data.getJSONArray("records");
                    int success = 0;  //入库数量
                    Date data_time = new Date();  //入库时间

                    for (int i=0; i<jsonArray.size(); i++) {
                        JSONObject records = jsonArray.getJSONObject(i);
                        //解析json数据
                        String date = records.getStr("date") == null ? "" : records.getStr("date");
                        String dateTime = records.getStr("dateTime") == null ? "" : records.getStr("dateTime");
                        String other = records.getStr("other") == null ? "" : records.getStr("other");
                        String address = records.getStr("address") == null ? "" : records.getStr("address");
                        String alertType = records.getStr("alertType") == null ? "" : records.getStr("alertType");
                        String pickTime = records.getStr("pickTime") == null ? "" : records.getStr("pickTime");
                        String endDate = records.getStr("endDate") == null ? "" : records.getStr("endDate");
                        String year = records.getStr("year") == null ? "" : records.getStr("year");
                        String important = records.getStr("important") == null ? "" : records.getStr("important");
                        String createdAt = records.getStr("createdAt") == null ? "" : records.getStr("createdAt");
                        String deletedAt = records.getStr("deletedAt") == null ? "" : records.getStr("deletedAt");
                        String alertCount = records.getStr("alertCount") == null ? "" : records.getStr("alertCount");
                        String street = records.getStr("street") == null ? "" : records.getStr("street");
                        String disposalSituation = records.getStr("disposalSituation") == null ? "" : records.getStr("disposalSituation");
                        String limit = records.getStr("limit") == null ? "" : records.getStr("limit");
                        String occurrenceTime = records.getStr("occurrenceTime") == null ? "" : records.getStr("occurrenceTime");
                        String disposalObject = records.getStr("disposalObject") == null ? "" : records.getStr("disposalObject");
                        String DATA_ID = records.getStr("id") == null ? "" : records.getStr("id");
                        String page = records.getStr("page") == null ? "" : records.getStr("page");
                        String time = records.getStr("time") == null ? "" : records.getStr("time");
                        String keyword = records.getStr("keyword") == null ? "" : records.getStr("keyword");
                        String startDate = records.getStr("startDate") == null ? "" : records.getStr("startDate");
                        String alertCategory = records.getStr("date") == null ? "" : records.getStr("alertCategory");
                        String updatedAt = records.getStr("updatedAt") == null ? "" : records.getStr("updatedAt");

                        Map<String, Object> paramMap = new HashMap<>();
                        paramMap.put("ID", idWorker.nextId()+"");
                        paramMap.put("DATE", date);
                        paramMap.put("DATETIME", dateTime);
                        paramMap.put("OTHER", other);
                        paramMap.put("ADDRESS", address);
                        paramMap.put("ALERTTYPE", alertType);
                        paramMap.put("PICKTIME", pickTime);
                        paramMap.put("ENDDATE", endDate);
                        paramMap.put("YEAR", year);
                        paramMap.put("IMPORTANT", important);
                        paramMap.put("CREATEDAT", createdAt);
                        paramMap.put("DELETEDAT", deletedAt);
                        paramMap.put("ALERTCOUNT", alertCount);
                        paramMap.put("STREET", street);
                        paramMap.put("DISPOSALSITUATION", disposalSituation);
                        paramMap.put("LIMIT", limit);
                        paramMap.put("OCCURRENCETIME", occurrenceTime);
                        paramMap.put("DISPOSALOBJECT", disposalObject);
                        paramMap.put("DATA_ID", DATA_ID);
                        paramMap.put("PAGE", page);
                        paramMap.put("TIME", time);
                        paramMap.put("KEYWORD", keyword);
                        paramMap.put("STARTDATE", startDate);
                        paramMap.put("ALERTCATEGORY", alertCategory);
                        paramMap.put("UPDATEDAT", updatedAt);
                        paramMap.put("DATA_TIME", data_time);

                        logger.info("城市预警--消防警情数据入库");
                        success += csYjMapper.saveXFJQ(paramMap);

                    }

                    return success;
                }
            }
        } catch (Exception e) {
            logger.info("城市预警--消防警情数据入库出错{}", e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }


}

package com.jinshan.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.dao.CsYxMapper;
import com.jinshan.service.CsYxAPIService;
import com.jinshan.util.http.HttpUtils;
import com.jinshan.util.IdWorker;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  城市运行服务层接口实现类
 * @Auther: 小宇宙
 * @Date: 2021-05-26 10:38
 */
@Service
public class CsYxAPIServiceImpl implements CsYxAPIService {

    @Autowired
    private CsYxMapper csYxMapper;

    @Autowired
    private IdWorker idWorker;
    /**
     *  城市环境
     *  水环境数据（市考，国考，出入境）
     *  获取考核断面数据,并将数据入库
     *  环境质量目标监控---水数据
     */
    @Override
    public void getKhDmData() {
        try {
            //请求远程接口
            String json = HttpUtils.sendGet(JinShanAPIEnum.getCsYxKhDmData.getUrl(), null);
            JSONObject jsonObject = JSONUtil.parseObj(json);
            JSONObject data = jsonObject.getJSONObject("data");

            int lyNum = data.getInt("lyNum");
            int skdbRate = data.getInt("skdbRate");
            int gkdbRate = data.getInt("gkdbRate");
            String szTypes = data.getStr("szTypes");
            int yyNum = data.getInt("yyNum");
            int skNum = data.getInt("skNum");
            String crjdbRate = data.getStr("crjdbRate");
            int crjNum = data.getInt("crjNum");
            String dbbmNum = data.getStr("dbbmNum");
            String dbRate = data.getStr("dbRate");
            int gkNum = data.getInt("gkNum");
            int pyNum = data.getInt("pyNum");

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", idWorker.nextId() + "");
            paramMap.put("lyNum", lyNum);
            paramMap.put("skdbRate", skdbRate);
            paramMap.put("gkdbRate", gkdbRate);
            paramMap.put("szTypes", szTypes);
            paramMap.put("yyNum", yyNum);
            paramMap.put("skNum", skNum);
            paramMap.put("crjdbRate", crjdbRate);
            paramMap.put("crjNum", crjNum);
            paramMap.put("dbbmNum", dbbmNum);
            paramMap.put("dbRate", dbRate);
            paramMap.put("gkNum", gkNum);
            paramMap.put("pyNum", pyNum);
            paramMap.put("update_time", new Date());
            //存入数据库
            int success = csYxMapper.khDmSave(paramMap);
            System.out.println("数据入库了" + success);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  获取实有人口数据，并将数据入库
     */
    @Override
    public void getSyRkData() {
        try {
            //请求远程地址
            String json = HttpUtils.sendPost(JinShanAPIEnum.getCsYxSyRkData.getUrl(), null);
            //解析json字符串
            JSONObject object = JSONUtil.parseObj(json);
            JSONObject jsonObject = object.getJSONObject("content").getJSONObject("jsPeopleCount");

            int sumPeople = jsonObject.getInt("sumPeople");         //总人口数据
            String typePeople = jsonObject.getStr("typePeople");    //人口分类数据

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", idWorker.nextId() + "");
            paramMap.put("sumPeople", sumPeople);
            paramMap.put("typePeople", typePeople);
            paramMap.put("update_time", new Date());

            //数据入库
            int success = csYxMapper.syRkSave(paramMap);
            System.out.println("数据入库了" + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取城市交通数据，并将数据入库
     */
    @Override
    public void getCsJtData() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer 77b07706a0ae88c3ef6f73712d8c29e6");
            // 带请求头的get请求
            JSONArray jsonArray = HttpUtils.sendGetheaders(JinShanAPIEnum.getCsYxCsJtData.getUrl(), headers);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", idWorker.nextId() + "");
            paramMap.put("jsonData", jsonArray.toString());
            paramMap.put("update_date", new Date());

            //数据入库
            int success = csYxMapper.csJtSave(paramMap);
            System.out.println("数据入库了" + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取城市环境大气数据，并将数据入库
     */
    @Override
    public void getCshJdqData() {
        try {
            //请求远程接口
            String json = HttpUtils.sendGet(JinShanAPIEnum.getCsYxCsHjDqData.getUrl(), null);
            //解析json字符串
            JSONObject object = JSONUtil.parseObj(json);
            JSONObject data = object.getJSONObject("data");

            String aqiDQRate = data.getStr("aqiDQRate");
            String aqiMBRate = data.getStr("aqiMBRate");
            String ybList = data.getJSONArray("ybList").toString();
            int aqiDQDay = data.getInt("aqiDQDay");
            String aqiMBDay = data.getStr("aqiMBDay");
            String aqiVal = data.getStr("aqiVal");
            String aqiStatus = data.getStr("aqiStatus");
            String aqiColor = data.getStr("aqiColor");
            String zdList = data.getJSONArray("zdList").toString();
            String aqiPic = data.getStr("aqiPic");

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", idWorker.nextId() + "");
            paramMap.put("aqiDQRate", aqiDQRate);
            paramMap.put("aqiMBRate", aqiMBRate);
            paramMap.put("ybList", ybList);
            paramMap.put("aqiDQDay", aqiDQDay);
            paramMap.put("aqiMBDay", aqiMBDay);
            paramMap.put("aqiVal", aqiVal);
            paramMap.put("aqiStatus", aqiStatus);
            paramMap.put("aqiColor", aqiColor);
            paramMap.put("zdList", zdList);
            paramMap.put("aqiPic", aqiPic);
            paramMap.put("update_date", new Date());
            //删除旧的数据
            int i = csYxMapper.deleteDqData();
            System.out.println("删除数据" + i);
            //数据入库
            int success = csYxMapper.cshJdqData(paramMap);
            System.out.println("数据入库了" + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

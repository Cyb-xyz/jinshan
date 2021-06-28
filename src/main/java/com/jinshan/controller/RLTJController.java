package com.jinshan.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.service.RLRJAPIService;
import com.jinshan.util.date.DateUtils;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *  区域人流数量
 *  移动人流人员
 *  获取实时人流统计
 * @Auther: 小宇宙
 * @Date: 2021-06-11 15:09
 */
@RestController
@RequestMapping("ssrl")
public class RLTJController {

    @Autowired
    private RLRJAPIService rlrjapiService;

    /**
     *  获取白天或晚上实时人流统计
     */
    @PostMapping(value = "getSsRlCount")
    public JSONObject getSsRlCount(@RequestBody String jsonStr) {

        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //粒度类型
        String grainedType = jsonObject.getStr("grainedType") == null ? "" : jsonObject.getStr("grainedType");
        //区域id
        String regionId = jsonObject.getStr("regionId") == null ? "" : jsonObject.getStr("regionId");
        //查询时间
        String queryDate = jsonObject.getStr("queryDate") == null ? "" : jsonObject.getStr("queryDate");

        int hour = Integer.parseInt(queryDate.substring(8, 10));

        //****************************1：先取总人数，查询区域人流接口*******************
        Map<String, String> headers = new HashMap<>();  //token令牌
        headers.put("x-access-token", getToken());
        Map<String, Object> param1 = new HashMap<>();    //请求体参数
        param1.put("grainedType", grainedType);
        param1.put("regionId", regionId);
        param1.put("queryDate", queryDate);

        JSONObject qyrlObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getQyRL.getUrl(), headers, param1);
        System.out.println("获取数据A数据");
        System.out.println(qyrlObject.toString());
        JSONObject qyrlData = qyrlObject.getJSONObject("data");
        int A = 0;
        if (qyrlData != null) {
            JSONArray currentData = qyrlData.getJSONArray("currentData");
            if (currentData != null) {
                for (int i=0; i < currentData.size(); i++) {
                    JSONObject obj = currentData.getJSONObject(i);
                    A = (Integer) obj.get("totalNum");
                }

            }
            // 将接口数据入库
            rlrjapiService.qyrlSave(qyrlData.toString());
        }
        System.out.println("A="+A);
        // 白天{6点，17点}
        if (hour >= 6 && hour <= 17) {
            System.out.println("白天");
            //*************************2:查询驻留时长接口,小时粒度，取第4个值***************
            int B = 0;
            // 获取startDate开始时间。小时粒度
            String startDate = DateUtils.addHourToDate(-4, queryDate, "yyyyMMddHH");
            Map<String, Object> param2 = new HashMap<>();    //请求体参数
            param2.put("grainedType", "HOUR");
            param2.put("regionId", regionId);
            param2.put("startDate", startDate); //开始时间
            param2.put("endDate", queryDate);   //结束时间
            JSONObject zlScObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getZlSc.getUrl(), headers, param2);
            System.out.println("获取数据B数据");
            System.out.println(zlScObject.toString());
            JSONObject zlscData = zlScObject.getJSONObject("data");
            if (zlscData != null) {
                JSONArray currentData = zlscData.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject residents = (JSONObject) currentData.get(i);
                        JSONArray residentsJsonArray = residents.getJSONArray("residentsJsonArray");
                        for (int j = 0; j < residentsJsonArray.size(); j++) {
                            JSONObject object = (JSONObject) residentsJsonArray.get(j);
                            if (object.getInt("time_section") == 4) {
                                B = object.getInt("person_num");
                            }
                        }
                    }
                }
                //存储驻留时长接口数据
                rlrjapiService.zlscSave(zlscData.toString());

            }
            System.out.println("B="+B);

            //*******************************查询驻留时长接口,分钟粒度，去第1、第2个值********************
            int C = 0;
            int D = 0;
            Map<String, Object> param3 = new HashMap<>();    //请求体参数
            param3.put("grainedType", "MIN");
            param3.put("regionId", regionId);
            param3.put("startDate", queryDate + "00");    //开始时间
            param3.put("endDate", queryDate + "59");      //结束时间
            JSONObject zlScObject2 = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getZlSc.getUrl(), headers, param3);
            JSONObject zlscData2 = zlScObject2.getJSONObject("data");
            if (zlscData2 != null) {
                JSONArray currentData = zlscData2.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject residents = (JSONObject) currentData.get(i);
                        JSONArray residentsJsonArray = residents.getJSONArray("residentsJsonArray");
                        for (int j = 0; j < residentsJsonArray.size(); j++) {
                            JSONObject object = (JSONObject) residentsJsonArray.get(j);
                            if (object.getInt("time_section") == 1) {
                                C = object.getInt("person_num");
                            }
                            if (object.getInt("time_section") == 2) {
                                D = object.getInt("person_num");
                            }
                        }
                    }
                }
                //存储驻留时长接口数据
                rlrjapiService.zlscSave(zlscData2.toString());

            }

            System.out.println("C="+C);
            System.out.println("D="+D);

            int num = A - B - C - D;
            double x = 0.7;
            DecimalFormat df = new DecimalFormat("0.000000000000");
            String format = df.format(num / x);
            double v = Double.parseDouble(format);
            System.out.println(v);

            //替换区域人流接口返回结果集中的totalNum值
            if (qyrlData != null) {
                JSONArray currentData = qyrlData.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject obj = currentData.getJSONObject(i);
                        obj.set("totalNum", v);
                    }
                }
            }
        }
        // 晚上{18点，05点}
        if ( (hour >= 18 && hour <= 24) || (hour >= 0 && hour <= 5) ) {
            System.out.println("晚上");

            //***********去常驻人数，查询驻留时长接口，入参22点-23点，分钟粒度，取第24个值,作为B
            String startDate = DateUtils.addHourToDate(-1, queryDate, "yyyyMMddHH");
            Map<String, Object> param2 = new HashMap<>();    //请求体参数
            param2.put("grainedType", "MIN");
            param2.put("regionId", regionId);
            param2.put("startDate", startDate + "00");    //开始时间
            param2.put("endDate", queryDate + "59");      //结束时间
            JSONObject zlScObject2 = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getZlSc.getUrl(), headers, param2);
            int B = 0;
            JSONObject zlscData = zlScObject2.getJSONObject("data");
            if (zlscData != null) {
                JSONArray currentData = zlscData.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject residents = (JSONObject) currentData.get(i);
                        JSONArray residentsJsonArray = residents.getJSONArray("residentsJsonArray");
                        for (int j = 0; j < residentsJsonArray.size(); j++) {
                            JSONObject object = (JSONObject) residentsJsonArray.get(j);
                            //取最后一个时间片的值
                            if (object.getInt("time_section") == residentsJsonArray.size()) {
                                B = object.getInt("person_num");
                            }
                        }
                    }
                }
                //存储驻留时长接口数据
                rlrjapiService.zlscSave(zlscData.toString());
            }

            //**********去路人，查询驻留时长接口，入参23点-23点，分钟粒度，去第1、第2个值，分别作为C、D
            int C = 0;
            int D = 0;
            Map<String, Object> param3 = new HashMap<>();    //请求体参数
            param3.put("grainedType", "MIN");
            param3.put("regionId", regionId);
            param3.put("startDate", queryDate + "00");    //开始时间
            param3.put("endDate", queryDate + "59");      //结束时间
            JSONObject zlScObject3 = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getZlSc.getUrl(), headers, param3);
            JSONObject zlscData2 = zlScObject3.getJSONObject("data");
            if (zlscData2 != null) {
                JSONArray currentData = zlscData2.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject residents = (JSONObject) currentData.get(i);
                        JSONArray residentsJsonArray = residents.getJSONArray("residentsJsonArray");
                        for (int j = 0; j < residentsJsonArray.size(); j++) {
                            JSONObject object = (JSONObject) residentsJsonArray.get(j);
                            if (object.getInt("time_section") == 1) {
                                C = object.getInt("person_num");
                            }
                            if (object.getInt("time_section") == 2) {
                                D = object.getInt("person_num");
                            }
                        }
                    }
                }
                //存储驻留时长接口数据
                rlrjapiService.zlscSave(zlscData2.toString());
            }

            int num = A - B - C - D;
            double x = 0.7;
            DecimalFormat df = new DecimalFormat("0.000000000000");
            String format = df.format(num / x);
            double v = Double.parseDouble(format);
            System.out.println(v);

            //替换区域人流接口返回结果集中的totalNum值
            if (qyrlData != null) {
                JSONArray currentData = qyrlData.getJSONArray("currentData");
                if (currentData != null) {
                    for (int i = 0; i < currentData.size(); i++) {
                        JSONObject obj = currentData.getJSONObject(i);
                        obj.set("totalNum", v);
                    }
                }
            }
        }

        return qyrlObject;
    }


    /**
     *  查询驻留时长接口
     * @param jsonStr
     * @return
     */
    @PostMapping(value = "getZlSc")
    public JSONObject getZlSc(@RequestBody String jsonStr) {

        Map<String, String> headers = new HashMap<>();  //token令牌
        headers.put("x-access-token", getToken());

        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //区域id
        String regionId = jsonObject.getStr("regionId") == null ? "" : jsonObject.getStr("regionId");
        //粒度类型
        String grainedType = jsonObject.getStr("grainedType") == null ? "" : jsonObject.getStr("grainedType");
        //开始时间
        String startDate = jsonObject.getStr("startDate") == null ? "" : jsonObject.getStr("startDate");
        //结束时间
        String endDate = jsonObject.getStr("endDate") == null ? "" : jsonObject.getStr("endDate");

        Map<String, Object> param = new HashMap<>();    //请求体参数
        param.put("regionId", regionId);        //区域id
        param.put("grainedType", grainedType);  //粒度类型
        param.put("startDate", startDate);      //开始时间
        param.put("endDate", endDate);          //结束时间
        JSONObject object = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getZlSc.getUrl(), headers, param);
        JSONObject zlscData = object.getJSONObject("data");
        if (zlscData != null) {
            //存储驻留时长接口数据
            rlrjapiService.zlscSave(zlscData.toString());
        } else {
            //存储驻留时长接口数据
            rlrjapiService.zlscSave(object.toString());
        }

        return  object;
    }

    /**
     *  查询区域人流画像基本信息接口
     * @param jsonStr
     * @return
     */
    @PostMapping(value = "getQyRlHxXX")
    public JSONObject getQyRlHxXX(@RequestBody String jsonStr) {

        Map<String, String> headers = new HashMap<>();  //token令牌
        headers.put("x-access-token", getToken());

        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //区域id
        String regionId = jsonObject.getStr("regionId") == null ? "" : jsonObject.getStr("regionId");
        //粒度类型
        String grainedType = jsonObject.getStr("grainedType") == null ? "" : jsonObject.getStr("grainedType");
        //开始时间
        String queryDate = jsonObject.getStr("queryDate") == null ? "" : jsonObject.getStr("queryDate");
        //画像类型
        String portraitType = jsonObject.getStr("portraitType") == null ? "" : jsonObject.getStr("portraitType");

        Map<String, Object> param = new HashMap<>();    //请求体参数
        param.put("regionId", regionId);                //区域id
        param.put("grainedType", grainedType);          //粒度类型
        param.put("queryDate", queryDate);              //开始时间
        param.put("portraitType", portraitType);        //画像类型

        JSONObject object = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getQyRlHxXX.getUrl(), headers, param);
        JSONObject data = object.getJSONObject("data");

        if (data != null) {
            //存储区域人流画像基本信息接口数据
            rlrjapiService.rlhxjbxxSave(data.toString());
        } else {
            //存储区域人流画像基本信息接口数据
            rlrjapiService.rlhxjbxxSave(object.toString());
        }

        return  object;
    }


    /**
     *  获取行政区全局热力（订阅-热力图）接口
     * @param jsonStr
     * @return
     */
    @PostMapping(value = "getXzQQjRL")
    public JSONObject getXzQQjRL(@RequestBody String jsonStr) {

        Map<String, String> headers = new HashMap<>();  //token令牌
        headers.put("x-access-token", getToken());

        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        //区县行政区编码
        String disCode = jsonObject.getStr("disCode") == null ? "" : jsonObject.getStr("disCode");
        //查询时间
        String queryDate = jsonObject.getStr("queryDate") == null ? "" : jsonObject.getStr("queryDate");

        Map<String, Object> param = new HashMap<>();    //请求体参数
        param.put("disCode", disCode);          //区县行政区编码
        param.put("queryDate", queryDate);      //查询时间

        JSONObject object = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getXzQQjRL.getUrl(), headers, param);
        JSONObject data = object.getJSONObject("data");

        if (data != null) {
            //存储行政区全局热力（订阅-热力图）接口数据
            rlrjapiService.xzqqjrlSave(data.toString());
        } else {
            //存储行政区全局热力（订阅-热力图）接口数据
            rlrjapiService.xzqqjrlSave(object.toString());
        }
        return  object;
    }

    /**
     *  获取token令牌
     * @return
     */
    public static String getToken() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("corpid", "bg4y4469");
        paramMap.put("secret", "7iEj74amt046W230EGX2Zj72DRmML63X");
        JSONObject json = JSONUtil.parseObj(HttpUtil.get(JinShanAPIEnum.getQyToken.getUrl(), paramMap));
        String token = json.getJSONObject("data").getStr("x-access-token");
        return token == null ? "" : token;
    }






}

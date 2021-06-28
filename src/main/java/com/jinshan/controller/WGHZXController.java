package com.jinshan.controller;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.pojo.JinShanResult;
import com.jinshan.pojo.Result;
import com.jinshan.service.WGHZXAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  区网格化中心指标定时任务控制层---接口定时任务层
 * @Auther: 小宇宙
 * @Date: 2021-06-23 18:56
 */
@RestController
@RequestMapping("wghzx")
public class WGHZXController {

    @Autowired
    private WGHZXAPIService wghzxapiService;

    private static Logger logger = LoggerFactory.getLogger(WGHZXController.class);

    /**
     *  新冠疫苗接种统计-街镇(疫苗类型)入库
     */
    @GetMapping(value = "/xgymjztjymlx")
    public JinShanResult saveXGYMJZTJ() {

        logger.info("进入新冠疫苗接种统计-街镇(疫苗类型)入库控制层");
        String token = getToken();  //获取请求token

        Map<String, String> headers = new HashMap<>();
        headers.put("x-access-token", token);
        Map<String, Object> requestBody = new HashMap<>();

        cn.hutool.json.JSONObject jsonObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getXGYMJZTJ.getUrl(), headers, requestBody);

        String jsonStr = jsonObject.toString();
        try {
            int success = wghzxapiService.saveXGYMJZTJ(jsonStr);
            return new JinShanResult("200", "新冠疫苗接种统计-街镇(疫苗类型)入库", "本次入库了"+success+"条数据" );
        } catch (Exception e) {
            logger.info("新冠疫苗接种统计-街镇(疫苗类型)入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "新冠疫苗接种统计-街镇(疫苗类型)入库异常", null);
    }


    /**
     *  新冠疫苗接种统计-街镇入库
     */
    @GetMapping(value = "/xgymjztjjz")
    public JinShanResult saveXGYMJZTJ_JZ() {

        logger.info("进入新冠疫苗接种统计-街镇入库控制层");
        String token = getToken();  //获取请求token

        Map<String, String> headers = new HashMap<>();
        headers.put("x-access-token", token);
        Map<String, Object> requestBody = new HashMap<>();

        cn.hutool.json.JSONObject jsonObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getXGYMJZTJ_JZ.getUrl(), headers, requestBody);

        String jsonStr = jsonObject.toString();
        try {
            int success = wghzxapiService.saveXGYMJZTJ_JZ(jsonStr);
            return new JinShanResult("200", "新冠疫苗接种统计-街镇入库", "本次入库了"+success+"条数据" );
        } catch (Exception e) {
            logger.info("新冠疫苗接种统计-街镇入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "新冠疫苗接种统计-街镇入库异常", null);
    }


    /**
     *  区人口-分年龄段统计(全区)数据入库
     */
    @GetMapping(value = "/qqnldtj")
    public JinShanResult saveQRKNLDTJ_QQ() {

        logger.info("进入区人口-分年龄段统计(全区)数据入库控制层");
        String token = getToken();  //获取请求token

        Map<String, String> headers = new HashMap<>();
        headers.put("x-access-token", token);
        Map<String, Object> requestBody = new HashMap<>();

        cn.hutool.json.JSONObject jsonObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getQRKNLDTJ_QQ.getUrl(), headers, requestBody);

        String jsonStr = jsonObject.toString();
        try {
            int success = wghzxapiService.saveQRKNLDTJ_QQ(jsonStr);
            return new JinShanResult("200", "区人口-分年龄段统计(全区)数据入库", "本次入库了"+success+"条数据" );
        } catch (Exception e) {
            logger.info("区人口-分年龄段统计(全区)数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "区人口-分年龄段统计(全区)数据入库异常", null);
    }


    /**
     *  区人口-分年龄段统计(街镇)数据入库
     */
    @GetMapping(value = "/jznldtj")
    public JinShanResult saveQRKNLDTJ_JZ() {

        logger.info("进入区人口-分年龄段统计(街镇)数据入库控制层");
        String token = getToken();  //获取请求token

        Map<String, String> headers = new HashMap<>();
        headers.put("x-access-token", token);
        Map<String, Object> requestBody = new HashMap<>();

        cn.hutool.json.JSONObject jsonObject = HttpUtils.sendPostHeaderJSONObject(JinShanAPIEnum.getQRKNLDTJ_JZ.getUrl(), headers, requestBody);

        String jsonStr = jsonObject.toString();
        try {
            int success = wghzxapiService.saveQRKNLDTJ_JZ(jsonStr);
            return new JinShanResult("200", "区人口-分年龄段统计(街镇)数据入库", "本次入库了"+success+"条数据" );
        } catch (Exception e) {
            logger.info("区人口-分年龄段统计(街镇)数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "区人口-分年龄段统计(街镇)数据入库异常", null);
    }


    /**
     *  获取区人口-分年龄段统计（街镇）数据
     */
    @PostMapping(value = "/getQRKNLDTJ_JZ")
    public JSONObject getQRKNLDTJ_JZ() {
        logger.info("进入获取区人口-分年龄段统计（街镇）数据--->控制层");
        try {
            List<Map<String, String>> list =  wghzxapiService.getQRKNLDTJ_JZ();
//            return list.toString();
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "操作成功");
            map.put("code", 200);     //请求成功
            map.put("data", list);
            String json = JSON.toJSONString(map);
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            logger.info("获取区人口-分年龄段统计(街镇)数据Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "操作成功");
        map.put("code", 200);
        map.put("data", null);
        String json = JSON.toJSONString(map);
        return JSONObject.parseObject(json);

    }

    /**
     *  根据街道名称获取街镇最新的人员分段数据
     */
    @PostMapping(value = "/getJZRKFD")
    public JSONObject getJZRKFD(HttpServletRequest request) {
        logger.info("进入根据街道名称获取街镇最新的人员分段数据--->控制层");
        try {
            String regionName = request.getParameter("regionName") == null ? "" : request.getParameter("regionName");
            List<Map<String, String>> list = wghzxapiService.getJZRKFD(regionName);
            Map<String, Object> map = new HashMap<>();
            map.put("msg", "操作成功");
            map.put("code", 200);     //请求成功
            map.put("data", list);
            String json = JSON.toJSONString(map);
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            logger.info("根据街道名称获取街镇最新的人员分段数据Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "操作成功");
        map.put("code", 200);
        map.put("data", null);
        String json = JSON.toJSONString(map);
        return JSONObject.parseObject(json);
    }

    /**
     *  获取token
     */
    public static String getToken() {
        logger.info("获取网格化中心接口token");
        String param = "corpid=12298qz6&secret=Mf9kC3AIW8atE3v04V8atP7Hh07q1p6l";
        String jsonToken = HttpUtils.sendGet(JinShanAPIEnum.getQWGHZXToken.getUrl(), param);
        JSONObject jsonObject = JSONObject.parseObject(jsonToken);
        if (jsonObject != null) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null) {
                return data.getString("x-access-token") == null ? "" : data.getString("x-access-token");
            }
        }
        return "";
    }

}

package com.jinshan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.DaPingAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  大屏指标入库---接口定时任务层。
 *  使用DIPC平台调用接口来实现定时任务。
 * @Auther: 小宇宙
 * @Date: 2021-06-19 23:03
 */
@RestController
@RequestMapping("/daPing")
public class DaPingController {

    @Autowired
    private DaPingAPIService daPingAPIService;

    private static Logger logger = LoggerFactory.getLogger(DaPingController.class);


    /**
     *  大屏-小微站-模块（小微站模块数据）数据入库
     */
    @GetMapping(value = "/xwz")
    public JinShanResult saveXWZ() {

        logger.info("进入大屏-小微站-模块（小微站模块数据）控制层");

        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String jsonStr = HttpUtils.sendGet(JinShanAPIEnum.getDPXWZ.getUrl(), param);
        //数据入库
        int success = daPingAPIService.saveXWZ(jsonStr);

        return new JinShanResult("200", "大屏-小微站-模块（小微站模块数据）数据入库", "本次入库了"+success+"条数据");
    }

    /*****************获取最新小微站数据*******************/
    @PostMapping(value = "/selectTodayXWZ")
    public JSONObject selectTodayXWZ() {
        logger.info("进入获取最新小微站模块数据控制层");
        try {
            //获取小微站最新Echart雷达图数据
            List<Map<String, String>> list = daPingAPIService.selectLDTSJ();
            List<Map<String, String>> other = daPingAPIService.selectOther();

            Map<String, Object> result = new HashMap<>();

            for (int i=0; i<other.size(); i++) {
                Map<String, String> map1 = other.get(i);

                String BJNUM = map1.get("BJNUM") == null ? "" : String.valueOf(map1.get("BJNUM"));
                String DWNUM = map1.get("DWNUM") == null ? "" : String.valueOf(map1.get("DWNUM"));
                String WRSYNUM = map1.get("WRSYNUM") == null ? "" : String.valueOf(map1.get("WRSYNUM"));

                result.put("BJNUM", BJNUM);
                result.put("DWNUM", DWNUM);
                result.put("WRSYNUM", WRSYNUM);
            }

            result.put("xwzList", list);

            Map<String, Object> map = new HashMap<>();
            map.put("msg", "请求成功");
            map.put("code", 200);     //请求成功
            map.put("data", result);
            String json = JSON.toJSONString(map);
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            logger.info("获取最新小微站模块数据Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "请求成功");
        map.put("code", 200);
        map.put("data", "获取最新小微站模块数据异常");

        String json = JSON.toJSONString(map);
        return JSONObject.parseObject(json);
    }


    /**
     *  大屏-扬尘污染-模块（扬尘污染模块）数据入库
     */
    @GetMapping(value = "/ycwr")
    public JinShanResult saveYCWR() {

        logger.info("进入大屏-扬尘污染-模块（扬尘污染模块）控制层");

        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String jsonStr = HttpUtils.sendGet(JinShanAPIEnum.getDPYCWR.getUrl(), param);

        //数据入库
        int success = daPingAPIService.saveYCWR(jsonStr);

        return new JinShanResult("200", "大屏-扬尘污染-模块（扬尘污染模块）数据入库", "本次入库了"+success+"条数据");
    }


    /**
     *  大屏-重点排污单位-模块（重点排污模块数据）数据入库
     */
    @GetMapping(value = "/zdpwdw")
    public JinShanResult saveZDPWDW() {

        logger.info("进入大屏-重点排污单位-模块（重点排污模块数据）控制层");

        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String jsonStr = HttpUtils.sendGet(JinShanAPIEnum.getDPZDPWDW.getUrl(), param);

        //数据入库
        int success = daPingAPIService.saveZDPWDW(jsonStr);

        return new JinShanResult("200", "大屏-重点排污单位-模块（重点排污模块数据）", "本次入库了"+success+"条数据");
    }



    /*****************获取最新重点排污单位数据*******************/
    @PostMapping(value = "/selectTodayPWDW")
    public JSONObject selectTodayPWDW() {
        logger.info("进入获取最新重点排污单位模块数据控制层");
        try {
            //获取小重点排污单位数据列表
            List<Map<String, String>> list = daPingAPIService.selectPwList();
            //获取小重点排污单位总和数据
            List<Map<String, String>> sum = daPingAPIService.selectPwSUM();

            Map<String, Object> result = new HashMap<>();

            for (int i=0; i<sum.size(); i++) {
                Map<String, String> map1 = sum.get(i);

                String TOTAL = map1.get("TOTAL") == null ? "" : map1.get("TOTAL");
                String FQNUM = map1.get("FQNUM") == null ? "" : map1.get("FQNUM");
                String FSNUM = map1.get("FSNUM") == null ? "" : map1.get("FSNUM");
                String YEAR = map1.get("YEAR") == null ? "" : map1.get("YEAR");

                result.put("TOTAL", TOTAL);
                result.put("FQNUM", FQNUM);
                result.put("FSNUM", FSNUM);
                result.put("YEAR", YEAR);
            }
            result.put("pwList", list);

            Map<String, Object> map = new HashMap<>();
            map.put("msg", "请求成功");
            map.put("code", 200);
            map.put("data", result);
            String json = JSON.toJSONString(map);
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            logger.info("获取最新重点排污单位模块数据Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "请求成功");
        map.put("code", 200);
        map.put("data", "获取最新重点排污单位模块数据异常");

        String json = JSON.toJSONString(map);
        return JSONObject.parseObject(json);
    }






}

package com.jinshan.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  大屏指标接口数据转发模块
 * @Auther: 小宇宙
 * @Date: 2021-06-18 12:47
 */
@RestController
@RequestMapping("dp")
public class DPController {

    private static Logger logger = LoggerFactory.getLogger(DPController.class);



    /**
     *  获取大屏重点排污单位模块数据
     */
    @GetMapping(value = "/getDPZDPWDW")
    public JSONObject getDPZDPWDW() {
        logger.info("获取大屏重点排污单位模块数据");
        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String str = HttpUtils.sendGet(JinShanAPIEnum.getDPZDPWDW.getUrl(), param);
        return JSONUtil.parseObj(str);
    }







}

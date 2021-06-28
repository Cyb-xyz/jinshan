package com.jinshan.controller;

import cn.hutool.json.JSONArray;
import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.CSJTAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  城市交通停车场指标---接口定时任务层。
 *  使用DIPC平台调用接口来实现定时任务。
 * @Auther: 小宇宙
 * @Date: 2021-06-26 14:42
 */
@RestController
@RequestMapping("/csjt")
public class CSJTController {

    @Autowired
    private CSJTAPIService csjtapiService;

    private static Logger logger = LoggerFactory.getLogger(CSJTController.class);


    /**
     *  停车场数据入库
     */
    @GetMapping(value = "/saveTCC")
    public JinShanResult saveTCC() {

        logger.info("进入停车场数据入库控制层");

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer 77b07706a0ae88c3ef6f73712d8c29e6");
            // 带请求头的get请求
            JSONArray jsonArray = HttpUtils.sendGetheaders(JinShanAPIEnum.getCsYxCsJtData.getUrl(), headers);
            //数据入库
            int success = csjtapiService.saveTCC(jsonArray);
            return new JinShanResult("200", " 停车场数据入库", "本次入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("停车场数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

        return new JinShanResult("400", "停车场数据入库出现异常", null);

    }


















}

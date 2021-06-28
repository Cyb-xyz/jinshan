package com.jinshan.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  大屏扬尘污染模块
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("dpycwr")
public class YcwrController {


    private static Logger logger = LoggerFactory.getLogger(YcwrController.class);



    /**
     *  获取大屏扬尘污染模块
     */
    @GetMapping
    public JSONObject getDPYCWR() {
        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String str = HttpUtils.sendGet(JinShanAPIEnum.getDPYCWR.getUrl(), param);
        return JSONUtil.parseObj(str);
    }



}

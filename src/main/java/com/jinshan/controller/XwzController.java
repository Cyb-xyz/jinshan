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
 *  大屏小微站模块数据
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("dpxwz")
public class XwzController {


    private static Logger logger = LoggerFactory.getLogger(XwzController.class);



    /**
     *  获取大屏小微站模块数据
     */
    @GetMapping
    public JSONObject getDPXWZ() {
        String param = "userToken=adffb309bc97721be36d618bb6f37df1";
        String str = HttpUtils.sendGet(JinShanAPIEnum.getDPXWZ.getUrl(), param);
        return JSONUtil.parseObj(str);
    }



}
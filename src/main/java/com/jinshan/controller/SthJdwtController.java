package com.jinshan.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 *  生态环境点位图
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("sthjdwt")
public class SthJdwtController {


    private static Logger logger = LoggerFactory.getLogger(SthJdwtController.class);


    /**
     *  获取生态环境点位图
     */
    @GetMapping
    public JSONObject getDPTZWR(HttpServletRequest request) {
        String category = request.getParameter("category");
        String param = "";
        if (category != null) {
            param = "?category="+category;
        }
        String url = JinShanAPIEnum.getSTHJDWT.getUrl()+param;
        System.out.println(url);
        String str = HttpUtil.get(url);
        return JSONUtil.parseObj(str);
    }



}

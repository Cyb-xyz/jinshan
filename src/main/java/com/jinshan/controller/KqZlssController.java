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
 *  获取空气质量API实时数据
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("kqzlsssj")
public class KqZlssController {


    private static Logger logger = LoggerFactory.getLogger(KqZlssController.class);


    /**
     *  获取空气质量API实时数据
     */
    @GetMapping
    public JSONObject getCsYxCsHjDqData() {
        String str = HttpUtil.get(JinShanAPIEnum.getCsYxCsHjDqData.getUrl());
        return JSONUtil.parseObj(str);
    }



}

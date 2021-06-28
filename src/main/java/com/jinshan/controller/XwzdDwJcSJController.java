package com.jinshan.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 *  获取小微站点位监测数据小时数据
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("xwzdjcsjxssj")
public class XwzdDwJcSJController {


    private static Logger logger = LoggerFactory.getLogger(XwzdDwJcSJController.class);


    /**
     *  获取小微站点位监测数据小时数据
     */
    @GetMapping
    public JSONObject getXWZDJCSJXSSJ(@RequestParam("category") String category,
                                      @RequestParam("id") String id,
                                      @RequestParam("item") String item ) {


        StringBuffer param = new StringBuffer();
        param.append("?category="+category);
        param.append("&id="+id);
        param.append("&item="+item);

        String url = JinShanAPIEnum.getXWZDJCSJXSSJ.getUrl()+param.toString();
        String str = HttpUtil.get(url);
        return JSONObject.parseObject(str);
    }



}

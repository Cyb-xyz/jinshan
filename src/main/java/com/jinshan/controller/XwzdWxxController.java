package com.jinshan.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 *  获取小微站点位信息
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("xwzdwxx")
public class XwzdWxxController {


    private static Logger logger = LoggerFactory.getLogger(XwzdWxxController.class);


    /**
     *  获取小微站点位信息
     */
    @GetMapping
    public JSONObject getXWZDWXX(HttpServletRequest request) {

        StringBuffer param = new StringBuffer();

        String town = request.getParameter("town");//根据街镇查询
        if (town != null) {
            param.append("&town="+town);
        }
        String pageNo = request.getParameter("pageNo");//页码
        if (pageNo != null) {
            param.append("&pageNo="+pageNo);
        }
        String pageSize = request.getParameter("pageSize");//每页数据，默认10
        if (pageSize != null) {
            param.append("&pageSize="+pageSize);
        }

        String str = HttpUtils.sendGet(JinShanAPIEnum.getXWZDWXX.getUrl(), param.toString());
        return JSONUtil.parseObj(str);
    }



}

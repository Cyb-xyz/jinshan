package com.jinshan.controller;


import com.alibaba.fastjson.JSONObject;
import com.jinshan.service.XFAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *  对外获取消防数据
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("xf")
public class XFController {

    @Autowired
    private XFAPIService xfapiService;

    private static Logger logger = LoggerFactory.getLogger(XFController.class);

    /**
     *  获取消防警情事件数据
     * @param request
     * @return
     */
    @GetMapping(value = "/XfJqSj")
    public JSONObject getXfJqSj(HttpServletRequest request) {
        //拼接请求参数
        StringBuffer param = new StringBuffer();

        String page = request.getParameter("page");//页码(默认1)
        if (page != null) {
            param.append("&page="+page);
        }
        String limit = request.getParameter("limit");//页面条数(默认20)
        if (limit != null) {
            param.append("&limit="+limit);
        }
        String startDate = request.getParameter("startDate");//起始时间(yyyy-MM-dd hh:mm:ss)
        if (startDate != null) {
            param.append("&startDate="+startDate);
        }
        String endDate = request.getParameter("endDate");//结束时间(yyyy-MM-dd hh:mm:ss)
        if (endDate != null) {
            param.append("&endDate="+endDate);
        }
        String alertType = request.getParameter("alertType");//警情类别()
        if (alertType != null) {
            param.append("&alertType="+alertType);
        }
        String street = request.getParameter("street");//街道名称
        if (street != null) {
            param.append("&street="+street);
        }

        String json = "";
        try {
            json = HttpUtils.sendGet(JinShanAPIEnum.getXFJQSj.getUrl(), param.toString());
            //消防警情事件数据入库
            //xfapiService.xfJqSjSave(json);
        } catch (Exception e) {
            logger.info("消防警情事件数据入库出错{}", e.getMessage());
            e.printStackTrace();
        }

        return JSONObject.parseObject(json);
    }


}

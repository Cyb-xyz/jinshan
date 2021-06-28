package com.jinshan.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *  危化接口数据转发层
 * @Auther: 小宇宙
 * @Date: 2021-06-02 17:19
 */
@RestController
@RequestMapping("wh")
public class WhController {

    /**
     *  获取企业信息总览信息
     */
    @GetMapping(value = "/qyxxzl")
    public JSONObject getQyXXZl() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getQyXXZl.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取危险化学品总览
     */
    @GetMapping(value = "/wxhxpzl")
    public JSONObject getWXHXPZl() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getWXHXPZl.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取流动流向-前日累计（辆）
     */
    @GetMapping(value = "/lslxqrlj")
    public JSONObject getLDLXQRLJ() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getLDLXQRLJ.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取流动流向-获取实时数量（辆）
     */
    @GetMapping(value = "/lslxsssl")
    public JSONObject getLDLXSSSl() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getLDLXSSSl.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取流动流向-获取危险船舶
     */
    @GetMapping(value = "/lslxWxcb")
    public JSONObject getLDLXWXCB() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getLDLXWXCB.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取流动流向-获取地下化工管线
     */
    @GetMapping(value = "/lslxdxhggx")
    public JSONObject getLDLXDXHGGX() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getLDLXDXHGGX.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

    /**
     *  获取监管动态总览
     */
    @GetMapping(value = "/jgdtzl")
    public JSONObject getJGDTZL() {
        String str = HttpUtils.sendGet(JinShanAPIEnum.getJGDTZL.getUrl(), null);
        return JSONUtil.parseObj(str);
    }

}

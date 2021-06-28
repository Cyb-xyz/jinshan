package com.jinshan.controller;

import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.WeiHuaAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  危化指标入库---接口定时任务层
 * @Auther: 小宇宙
 * @Date: 2021-06-19 22:56
 */
@RestController
@RequestMapping("weiHua")
public class WeiHuaController {

    @Autowired
    private WeiHuaAPIService weiHuaAPIService;

    private static Logger logger = LoggerFactory.getLogger(WeiHuaController.class);

    /**
     *  企业信息总览信息入库
     */
    @GetMapping(value = "/qyxxzl")
    public JinShanResult qyxxzlSave() {
        logger.info("进入危化指标---获取企业信息总览控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getQyXXZl.getUrl(), null);
            int success = weiHuaAPIService.save_qyxxzl(result);
            return new JinShanResult("200", "危化指标--获取企业信息总览", "入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("企业信息总览信息入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "企业信息总览信息入库出错", null);
    }



    /**
     *  危险化学品总览数据入库
     */
    @GetMapping(value = "/wxhxpzl")
    public JinShanResult saveWXHXPZL() {
        logger.info("进入危化指标---危险化学品总览数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getWXHXPZl.getUrl(), null);
            int success = weiHuaAPIService.saveWXHXPZL(result);
            return new JinShanResult("200", "危化指标--获取危险化学品总览", "危险化学品总览数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("危险化学品总览数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "危险化学品总览数据入库出错", null);
    }


    /**
     *  危化车辆，前日累计（辆）数据入库
     */
    @GetMapping(value = "/whcldrlj")
    public JinShanResult saveQRLJCL() {
        logger.info("进入危化车辆，前日累计（辆）数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getLDLXQRLJ.getUrl(), null);
            int success = weiHuaAPIService.saveQRLJCL(result);
            return new JinShanResult("200", "危化指标--危化车辆，前日累计（辆）数据入库", "危化车辆，前日累计（辆）数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("危化车辆，前日累计（辆）数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "危化车辆，前日累计（辆）数据入库出错", null);
    }


    /**
     *  危化车辆，实时数量（辆）数据入库
     */
    @GetMapping(value = "/whsssl")
    public JinShanResult saveSSCSL() {
        logger.info("进入危化车辆，实时数量（辆）数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getLDLXSSSl.getUrl(), null);
            int success = weiHuaAPIService.saveSSCSL(result);
            return new JinShanResult("200", "危化指标--危化车辆，实时数量（辆）数据入库", "危化车辆，实时数量（辆）数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("危化车辆，实时数量（辆）数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "危化车辆，实时数量（辆）数据入库出错", null);
    }


    /**
     *  危险船舶数据入库
     */
    @GetMapping(value = "/wxcb")
    public JinShanResult saveWXCB() {
        logger.info("进入危险船舶数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getLDLXWXCB.getUrl(), null);
            int success = weiHuaAPIService.saveWXCB(result);
            return new JinShanResult("200", "危险船舶数据入库", "危险船舶数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("危险船舶数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "危险船舶数据入库出错", null);
    }


    /**
     *  地下化工管线数据入库
     */
    @GetMapping(value = "/dxhggx")
    public JinShanResult saveDXHGGX() {
        logger.info("进入地下化工管线数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getLDLXDXHGGX.getUrl(), null);
            int success = weiHuaAPIService.saveDXHGGX(result);
            return new JinShanResult("200", "地下化工管线数据入库", "地下化工管线数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("地下化工管线数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "地下化工管线数据入库出错", null);
    }


    /**
     *  监管动态总览数据入库
     */
    @GetMapping(value = "/jgdtzl")
    public JinShanResult saveJGDTZL() {
        logger.info("进入监管动态总览数据入库控制层");
        try {
            String result = HttpUtils.sendGet(JinShanAPIEnum.getJGDTZL.getUrl(), null);
            int success = weiHuaAPIService.saveJGDTZL(result);
            return new JinShanResult("200", "监管动态总览数据入库", "监管动态总览数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("监管动态总览数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "监管动态总览数据入库出错", null);
    }



















}

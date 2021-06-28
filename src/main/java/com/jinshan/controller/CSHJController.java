package com.jinshan.controller;

import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.CSHJAPIService;
import com.jinshan.service.DaPingAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  城市环境指标---接口定时任务层。
 *  使用DIPC平台调用接口来实现定时任务。
 * @Auther: 小宇宙
 * @Date: 2021-06-19 23:03
 */
@RestController
@RequestMapping("/cshj")
public class CSHJController {

    @Autowired
    private CSHJAPIService cshjapiService;

    private static Logger logger = LoggerFactory.getLogger(CSHJController.class);


    /**
     *  空气质量API实时数据入库
     */
    @GetMapping(value = "/kqzl")
    public JinShanResult saveKQZL() {

        logger.info("进入空气质量API实时数据入库控制层");

        String jsonStr = HttpUtils.sendGet(JinShanAPIEnum.getCsYxCsHjDqData.getUrl(), null);

        try {
            //数据入库
            int success = cshjapiService.saveKQZL(jsonStr);
            return new JinShanResult("200", "空气质量API实时数据入库", "本次入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("空气质量API实时数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

        return new JinShanResult("400", "空气质量API实时数据入库异常", null);

    }



    /**
     *  城市环境考核断面数据入库
     */
    @GetMapping(value = "/khdm")
    public JinShanResult saveKHDM() {

        logger.info("进入城市环境考核断面数据入库控制层");

        String jsonStr = HttpUtils.sendGet(JinShanAPIEnum.getCsYxKhDmData.getUrl(), null);

        try {
            //数据入库
            int success = cshjapiService.saveKHDM(jsonStr);
            return new JinShanResult("200", "城市环境考核断面数据入库", "本次入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("城市环境考核断面数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

        return new JinShanResult("400", "城市环境考核断面数据入库异常", null);

    }















}

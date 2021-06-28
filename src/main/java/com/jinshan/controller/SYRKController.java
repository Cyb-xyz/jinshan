package com.jinshan.controller;

import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.CSHJAPIService;
import com.jinshan.service.SYRKAPIService;
import com.jinshan.util.enumerate.JinShanAPIEnum;
import com.jinshan.util.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  实有人口指标---接口定时任务层。
 *  使用DIPC平台调用接口来实现定时任务。
 * @Auther: 小宇宙
 * @Date: 2021-06-26 13:42
 */
@RestController
@RequestMapping("/syrk")
public class SYRKController {

    @Autowired
    private SYRKAPIService syrkapiService;

    private static Logger logger = LoggerFactory.getLogger(SYRKController.class);


    /**
     *  实有人口数据入库
     */
    @GetMapping(value = "saveSYRK")
    public JinShanResult saveSYRK() {

        logger.info("进入实有人口数据入库控制层");

        try {
            //请求远程地址
            String json = HttpUtils.sendPost(JinShanAPIEnum.getCsYxSyRkData.getUrl(), null);
            //数据入库
            int success = syrkapiService.saveSYRK(json);
            return new JinShanResult("200", " 实有人口数据入库", "本次入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info(" 实有人口数据入库Controller出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

        return new JinShanResult("400", " 实有人口数据入库出现异常", null);

    }


















}

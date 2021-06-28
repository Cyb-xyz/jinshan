package com.jinshan.controller;

import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.CsYjAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  城市预警指标
 * @Auther: 小宇宙
 * @Date: 2021-06-21 16:28
 */
@RestController
@RequestMapping("/csyj")
public class CSYJController {

    @Autowired
    private CsYjAPIService csYjAPIService;

    private static Logger logger = LoggerFactory.getLogger(CSYJController.class);


    /**
     *  城市预警-公安数据入库
     */
    @GetMapping(value = "/ga")
    public JinShanResult saveCsYjGa() {
        logger.info("进入城市预警-公安数据入库控制层");
        try {
            int success = csYjAPIService.saveCsYjGa();
            return new JinShanResult("200", "城市预警-公安数据入库", "城市预警-公安数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("城市预警-公安数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "城市预警-公安数据入库出错", null);
    }


    /**
     *  城市预警--消防警情数据入库
     */
    @GetMapping(value = "/xfjq")
    public JinShanResult saveXFJQ() {
        logger.info("进入城市预警--消防警情数据入库控制层");
        try {
            int success = csYjAPIService.saveXFJQ();
            return new JinShanResult("200", "城市预警--消防警情数据入库", "城市预警--消防警情数据入库了"+success+"条数据");
        } catch (Exception e) {
            logger.info("城市预警--消防警情数据入库controller层出错，{}", e.getMessage());
            e.printStackTrace();
        }
        return new JinShanResult("400", "城市预警--消防警情数据入库出错", null);
    }

















}

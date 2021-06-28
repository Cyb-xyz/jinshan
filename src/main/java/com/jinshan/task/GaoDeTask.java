package com.jinshan.task;

import com.jinshan.controller.GaoDeController;
import com.jinshan.pojo.JinShanResult;
import com.jinshan.service.YiTuAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *  高德定时任务测试
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:58
 */
@Configuration
public class GaoDeTask {

    @Autowired
    private GaoDeController gaoDeController;

    /**
     *  高德_交通路况信息入库，每5分钟请求一次
     */
//    @Scheduled(fixedRate = 60000 * 5)
    public void saveJtLkXX() throws Exception {
        System.out.println("高德_交通路况信息入库");
        JinShanResult jinShanResult = gaoDeController.saveJtLkXX();
        System.out.println(jinShanResult.toString());
    }


    /**
     *  高德_交通路况道路ID信息入库，每5分钟请求一次
     */
//    @Scheduled(cron = "0 0/5 * * * ?")
    public void getToken() throws Exception {
        System.out.println("高德_交通路况道路ID信息入库");
        JinShanResult jinShanResult = gaoDeController.saveJtLkIDXX();
        System.out.println(jinShanResult.toString());
    }
}

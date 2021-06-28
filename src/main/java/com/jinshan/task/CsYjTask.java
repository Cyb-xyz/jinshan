package com.jinshan.task;

import com.jinshan.service.CsYjAPIService;
import com.jinshan.service.YiTuAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *  城市预警定时任务
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:58
 */
@Configuration
public class CsYjTask {

    @Autowired
    private CsYjAPIService csYjAPIService;

    /**
     *  --------------------废弃
     *  获取最新城市预警--公安数据，每5分钟请求一次-------------------已经用dipc控制定时任务
     */
//    @Scheduled(fixedRate = 60000 * 5)
    public void getCsYjGaData() {
        System.out.println("获取最新城市预警--公安数据");
//        try {
//            csYjAPIService.getCsYjGaData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}

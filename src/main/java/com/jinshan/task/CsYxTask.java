package com.jinshan.task;

import com.jinshan.service.CsYxAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *  城市运行定时任务
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:58
 */
@Configuration
public class CsYxTask {

    @Autowired
    private CsYxAPIService csYxAPIService;

    /**
     *  城市环境--------------------废弃
     *  水环境数据（市考，国考，出入境）
     *  获取最新城市运行--获取考核断面数据,并将数据入库，每10分钟请求一次
     */
//    @Scheduled(fixedRate = 60000 * 10)
    public void getKhDmData() {
        System.out.println("获取最新城市运行--获取考核断面数据");
        try {
            csYxAPIService.getKhDmData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  废弃
     *  获取最新城市运行--获取实有人口数据,并将数据入库，从程序启动的那一刻，每6小时更新一次
     */
//    @Scheduled(fixedRate = 60000 * 60 * 6)
    public void getSyRkData() {
        System.out.println("获取最新城市运行--获取实有人口数据");
        try {
            csYxAPIService.getSyRkData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  废弃
     *  获取城市运动--城市交通数据，并将数据入库，从程序启动的那一刻，每15分钟更新一次
     */
//    @Scheduled(fixedRate = 60000 * 15)
    public void getCsJtDate() {
        System.out.println("获取城市运动--城市交通数据");
        try {
            csYxAPIService.getCsJtData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  --------------------废弃
     *  获取城市环境大气数据，并将数据入库，从程序启动的那一刻，每1分钟更新一次
     *
     */
//    @Scheduled(fixedRate = 60000)
    public void getCshJdqData() {
        System.out.println("获取城市运动--城市环境大气数据");
        try {
            csYxAPIService.getCshJdqData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}

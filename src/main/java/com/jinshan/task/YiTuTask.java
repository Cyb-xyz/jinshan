package com.jinshan.task;

import com.jinshan.service.YiTuAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sound.midi.Soundbank;

/**
 * @Auther: 小宇宙
 * @Date: 2021-05-18 23:58
 */
@Configuration
public class YiTuTask {

    @Autowired
    private YiTuAPIService yiTuAPIService;

    /**
     *  获取令牌，每分钟请求一次
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
    public void getToken() {
        System.out.println("获取令牌");
        String token = yiTuAPIService.getToken();
        System.out.println("用户令牌为:"+token);
    }
}

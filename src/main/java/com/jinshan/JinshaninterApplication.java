package com.jinshan;

import com.jinshan.util.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan("com.jinshan.dao")
@EnableScheduling
public class JinshaninterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinshaninterApplication.class, args);
        System.out.println();
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }

}

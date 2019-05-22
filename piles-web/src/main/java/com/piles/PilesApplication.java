package com.piles;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling//开启定时任务
@EnableAsync//开启异步
@MapperScan("com.piles.core.mapper")
public class PilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PilesApplication.class, args);
        log.info(PilesApplication.class.getSimpleName() + " is success!");
    }
}

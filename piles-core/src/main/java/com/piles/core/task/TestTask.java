package com.piles.core.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class TestTask {
 
    // 定义每过3秒执行任务
//    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        log.info("time is :{}", LocalDateTime.now());
    }
}

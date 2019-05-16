package com.piles.web.service.impl;

import com.google.common.collect.Lists;
import com.piles.sdk.entity.Station;
import com.piles.web.service.IStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/14 14:05
 * @Description:
 */
@Slf4j
@Service
public class StationServiceImpl implements IStationService {
    @Override
    public List<Station> getStationList() {
        StopWatch stopWatch = new StopWatch("查询列表");
        stopWatch.start("调用远程查询数据");
        List<Station> result = Lists.newArrayList();
        for(int i = 0;i<50;i++){
            Station station = new Station();
            station.setId(i);
            station.setName("name" + i);
            station.setCreateTime(LocalDateTime.now());
            station.setDesc("描述上阿斯顿发生飞仨" + i);
            result.add(station);
        }
        stopWatch.stop();
        stopWatch.start("开始组装数据");
        stopWatch.stop();
        log.info("总耗时:{}",stopWatch.prettyPrint());
        return result;
    }
}

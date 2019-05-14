package com.piles.web.service.impl;

import com.google.common.collect.Lists;
import com.piles.sdk.entity.Station;
import com.piles.web.service.IStationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/14 14:05
 * @Description:
 */
@Service
public class StationServiceImpl implements IStationService {
    @Override
    public List<Station> getStationList() {
        List<Station> result = Lists.newArrayList();
        for(int i = 0;i<50;i++){
            Station station = new Station();
            station.setId(i);
            station.setName("name" + i);
            station.setCreateTime(LocalDateTime.now());
            station.setDesc("描述上阿斯顿发生飞仨" + i);
            result.add(station);
        }
        return result;
    }
}

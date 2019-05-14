package com.piles.sdk.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/14 14:02
 * @Description:
 */
@Data
public class Station {
    private int id;
    private String name;
    private String desc;
    private LocalDateTime createTime;
}

package com.piles.sdk.entity;

import lombok.Data;

/**
 * @Auther: zhanglizhi
 * @Date: 2019/5/14 17:56
 * @Description:
 */
@Data
public class BaseQueryParam {

    /**
     * 对应layui传递的页码
     */
    private int page;
    /**
     * 对应layui传递的每页数量
     */
    private int limit;

}

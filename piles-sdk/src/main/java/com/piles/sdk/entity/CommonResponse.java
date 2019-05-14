package com.piles.sdk.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CommonResponse<T> implements Serializable {
    /**
     * 返回编码
     */
    private String code;
    /**
     * 信息
     */
    private String message;
    /**
     * 总数 layui分页使用
     */
    private int count;
    /**
     * 返回实体
     */
    private T data;

    //空的构造方法,code默认为"0"
    public CommonResponse() {
        this.code = "0";
    }

    //请求失败并自定义错误时的返回结果
    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //请求失败并自定义错误时的返回结果
    public CommonResponse(String code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //请求成功时返回的结果
    public CommonResponse(T data) {
        if (data == null) {
            this.code = "0";
            return;
        }
        this.code = "0";
        this.data = data;
    }

    public static CommonResponse success(){
        return new CommonResponse();
    }
}

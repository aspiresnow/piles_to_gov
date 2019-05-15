package com.piles.sdk.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TradeTypeEnum {

    INIT("0", "新建");

    private String code;
    private String name;
}

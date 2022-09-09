package com.yangteng.workbackstage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Lv {
    LV1(10L),LV2(100L),LV3(1000L),LV4(10000L),LV5(20000L),LV6(30000L);

    private Long exp;
}

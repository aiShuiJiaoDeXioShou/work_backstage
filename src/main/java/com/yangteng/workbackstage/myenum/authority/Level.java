package com.yangteng.workbackstage.myenum.authority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Level {
    Highest("最高级别！",1),
    Medium("中等级别" ,0);
    public static final Integer MEDIUM_LEVEL = 1;
    public static final Integer HIGHEST_LEVEL = 0;

    private String describe;
    private Integer code;
}

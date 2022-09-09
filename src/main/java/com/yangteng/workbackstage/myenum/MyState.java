package com.yangteng.workbackstage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MyState {
    PutShelves("未上架",0) ,
    NotShelves("上架",1);

    private String message;
    private Integer state;
}

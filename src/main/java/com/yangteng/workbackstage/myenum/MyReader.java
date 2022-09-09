package com.yangteng.workbackstage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MyReader {
    All_Reader("All_Reader") ,
    Ordinary_Vip("Ordinary_Vip") ,
    Plus_Vip("Plus_Vip") ;
    private String value;
}

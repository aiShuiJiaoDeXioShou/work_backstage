package com.yangteng.workbackstage.myenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MyResStatus {
    NoService(-5, "没有该项服务！"),ParametersError(-3, "参数错误"),
    NoLoginError(-2,"没有登录！"),
    NoAuthorizeError(-4,"没有该项权限！");
    private Integer status;
    private String description;
}

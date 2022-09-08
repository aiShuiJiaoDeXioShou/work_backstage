package com.yangteng.workbackstage.myenum.authority;

import lombok.Getter;

@Getter
public enum ManageAuthority {

    USER_MANAGE("用户管理权限",ManageAuthority.USER_MANAGE_,0),
    BOOK_MANAGE("图书管理权限",ManageAuthority.BOOK_MANAGE_,0),
    ADMIN("超级管理员",ManageAuthority.ADMIN_,1),;

    public static final String ADMIN_ = "admin";
    public static final String USER_MANAGE_ = "user_manage";
    public static final String BOOK_MANAGE_ = "book_manage";

    private String message;
    private String code;

    private Integer type;

    ManageAuthority(String message, String code, Integer type) {
        this.message = message;
        this.code = code;
        this.type = type;
    }


}

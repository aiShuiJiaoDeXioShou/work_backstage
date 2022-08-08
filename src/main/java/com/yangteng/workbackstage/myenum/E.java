package com.yangteng.workbackstage.myenum;

public enum E {
    NOT_LOGIN(-2),SUCCESS_CODE(0),ERROR_CODE(-1), NOT_FOUND(404);
    private final Integer code;

    E(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}

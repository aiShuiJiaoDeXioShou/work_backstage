package com.yangteng.workbackstage.comm;

import com.yangteng.workbackstage.myenum.E;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 林河
 *         这个是返回给前端的统一数据格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    private String msg;
    private Integer code;
    private Object data;
    private LocalDateTime time;

    public R(String msg, E e) {
        this.msg = msg;
        this.code = e.getCode();
        this.time = LocalDateTime.now();
    }

    public R(E e) {
        this.msg = "失败！";
        this.code = e.getCode();
        this.time = LocalDateTime.now();
    }

    public R(String msg, E e, Object data, LocalDateTime time) {
        this.msg = msg;
        this.code = e.getCode();
        this.data = data;
        this.time = time;
    }

    public void setCode(E e) {
        this.code = e.getCode();
    }

    public static <T> R<T> ok(T data) {
        return new R<>(SUCCESS, E.SUCCESS_CODE, data, LocalDateTime.now());
    }

    public static R ok(Object... data) {
        return new R(SUCCESS, E.SUCCESS_CODE, data, LocalDateTime.now());
    }

    public static R ok() {
        return new R(SUCCESS, E.SUCCESS_CODE, null, LocalDateTime.now());
    }

    public static R<String> fail(String msg) {
        return new R<String>(msg, E.ERROR_CODE, null, LocalDateTime.now());
    }

    public static R<String> fail() {
        return new R<String>(ERROR, E.ERROR_CODE, null, LocalDateTime.now());
    }
}

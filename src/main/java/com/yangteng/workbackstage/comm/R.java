package com.yangteng.workbackstage.comm;

import com.yangteng.workbackstage.myenum.E;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 林河
 *         这个是返回给前端的统一数据格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String msg;
    private Integer code;
    private Object data;
    private String time;

    public R(String msg, E e) {
        this.msg = msg;
        this.code = e.getCode();
        this.time = LocalDateTime.now().format(format);
    }

    public R(E e) {
        this.msg = "失败！";
        this.code = e.getCode();
        this.time = LocalDateTime.now().format(format);
    }

    public R(String msg, E e, Object data, LocalDateTime time) {
        this.msg = msg;
        this.code = e.getCode();
        this.data = data;
        this.time = time.format(format);
    }

    public void setCode(E e) {
        this.code = e.getCode();
    }

    public static R ok(Object data) {
        return new R(SUCCESS, E.SUCCESS_CODE, data, LocalDateTime.now());
    }

    public static R ok(Object... data) {
        return new R(SUCCESS, E.SUCCESS_CODE, data, LocalDateTime.now());
    }

    public static R ok() {
        return new R(SUCCESS, E.SUCCESS_CODE, null, LocalDateTime.now());
    }

    public static R fail(String msg) {
        return new R(msg, E.ERROR_CODE, null, LocalDateTime.now());
    }

    public static R fail() {
        return new R(ERROR, E.ERROR_CODE, null, LocalDateTime.now());
    }
}

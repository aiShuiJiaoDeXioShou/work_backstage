package com.yangteng.workbackstage.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;

import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.myenum.E;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = { RestController.class, Controller.class })
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R handler(NoHandlerFoundException exception) {
        log.error("亲，未找到该项服务哦！ 404");
        return new R("亲，未找到该项服务哦！ 404", E.NOT_FOUND);
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.NOT_EXTENDED)
    public R exHandler(NotLoginException exception) {
        // 这样处理数据库异常可还行
        log.error(exception.getMessage());
        return new R(exception.getMessage(), E.NOT_LOGIN);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R exHandler(SQLIntegrityConstraintViolationException exception) {
        // 这样处理数据库异常可还行
        final String message = exception.getMessage();
        StringBuffer res_msg = new StringBuffer();
        if (message.contains("Duplicate entry")) {
            final int start = "Duplicate entry '".length();
            final int end = "' for key 'work_user.phone'".length();
            res_msg.append(message, start, message.length() - end);
            log.error("{}已经存在", res_msg);
        }
        return R.fail(String.valueOf(res_msg.append("已经存在")));
    }

    @ExceptionHandler(NotRoleException.class)
    public R exHandler(NotRoleException exception) {
        return R.fail("亲,您没有这个权限哦！");
    }
}

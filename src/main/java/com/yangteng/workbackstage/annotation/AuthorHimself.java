package com.yangteng.workbackstage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加在这上面对方法进行作品验证,判断是不是该账号写的,
 * 或者只有管理员才能修改
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorHimself {
    
}

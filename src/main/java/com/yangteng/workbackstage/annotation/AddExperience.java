package com.yangteng.workbackstage.annotation;

import com.yangteng.workbackstage.myenum.Lv;
import com.yangteng.workbackstage.myenum.level.WordtreeLevel;
import com.yangteng.workbackstage.myenum.level.WordtreeReaderLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加在方法上面自动增长经验,根据不同的梯度来增长经验
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddExperience {

    Class<? extends WordtreeLevel > type() default WordtreeReaderLevel.class;

    Lv expLv();

    long value() default 20L;
}

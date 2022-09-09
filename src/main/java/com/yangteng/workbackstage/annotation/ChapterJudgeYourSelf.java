package com.yangteng.workbackstage.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 阅读判断器，该章节是否免费，是否该用户拥有该章节，是否为作者本人
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChapterJudgeYourSelf {
}

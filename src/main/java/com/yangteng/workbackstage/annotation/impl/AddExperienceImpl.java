package com.yangteng.workbackstage.annotation.impl;

import com.yangteng.workbackstage.annotation.AddExperience;
import com.yangteng.workbackstage.myenum.level.WordtreeAuthorLevel;
import com.yangteng.workbackstage.myenum.level.WordtreeReaderLevel;
import com.yangteng.workbackstage.service.IWorkUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j
public class AddExperienceImpl {

    @Autowired
    private IWorkUserService userService;

    /**
     * 根据不同类型添加 经验值
     */
    @Around("@annotation(com.yangteng.workbackstage.annotation.AddExperience)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable {
        AddExperience addExperience = joinPoint.getClass().getAnnotation(AddExperience.class);

        Long addExp;
        if (!Objects.isNull(addExperience.expLv())){
            addExp = addExperience.expLv().getExp();
        } else {
            addExp = addExperience.value();
        }

        if (addExperience.type() == WordtreeAuthorLevel.class){
            userService.addAuthorExp(addExp);
        }else if (addExperience.type() == WordtreeReaderLevel.class){
            userService.addReadExp(addExp);
        }

        return joinPoint.proceed();
    }


}

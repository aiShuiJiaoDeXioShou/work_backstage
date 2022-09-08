package com.yangteng.workbackstage.annotation.impl;

import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.myenum.authority.ManageAuthority;
import com.yangteng.workbackstage.entity.book.Book;
import com.yangteng.workbackstage.exception.MethodFormatException;
import com.yangteng.workbackstage.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 此类是对AuthorHimself注解的实现
 */
@Aspect
@Component
@Slf4j
public class AuthorHimselfImpl {

    @Autowired
    private IBookService workBookService;

    @Around("@annotation(com.yangteng.workbackstage.annotation.AuthorHimself)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 判断当前用户是否具有指定权限，如果没有则开启书籍验证
        boolean hasPermissionOr = StpUtil.hasPermissionOr(
                ManageAuthority.BOOK_MANAGE.getCode()
        );
        boolean hasRoleOr = StpUtil.hasRoleOr(ManageAuthority.ADMIN.getCode());
        if (hasPermissionOr || hasRoleOr) {
            // 直接过
            joinPoint.proceed();
            return joinPoint.proceed();
        }
        log.info("没有图书管理的权限--->{}","验证失败！");

        // 获得传入书籍的id
        Object[] args = joinPoint.getArgs();
        // 规定最后一个为书籍id，如果没有则抛方法格式错误异常
        Object obj =  args[args.length - 1];
        Long bookId = null;
        // 判断obj的格式
        if (obj instanceof Long) {
            bookId = (Long) obj;
        }else if (obj instanceof Book) {
            bookId = ((Book) obj).getId();
        }
        if (bookId == null) throw new MethodFormatException("方法格式错误");
        // 从数据库中查找这本书籍的作者如果匹配则验证成功
        Book byId = workBookService.getById(bookId);
        boolean bool = byId.getAutorId() == StpUtil.getLoginIdAsLong();
        if (!bool) throw new NotRoleException("您，没有该权限！");
        return joinPoint.proceed();
    }
}

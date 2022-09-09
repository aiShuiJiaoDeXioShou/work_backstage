package com.yangteng.workbackstage.annotation.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.book.Book;
import com.yangteng.workbackstage.entity.book.BookChapter;
import com.yangteng.workbackstage.myenum.MyReader;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ChapterJudgeYourSelfImpl {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookChapterService bookChapterService;


    @Around("@annotation(com.yangteng.workbackstage.annotation.ChapterJudgeYourSelf)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long bookId = null;
        Object arg = args[0];

        if (arg instanceof Long) {
            bookId = (Long) arg;
        } else if (arg instanceof Book){
            bookId =  ((Book) arg).getId();
        } else if (arg instanceof BookChapter){
            bookId = ((BookChapter) arg).getBookId();
        }

        assert bookId != null;

        Book book = bookService.getById(bookId);

        /*
          如果是作者的话后面的所有章节直接放行
          或者你拥有特定的权限
         */
        boolean permissionOr = StpUtil.hasPermissionOr(
                MyReader.All_Reader.getValue(),
                MyReader.Plus_Vip.getValue(),
                MyReader.Ordinary_Vip.getValue()
        );

        if (book.getAutorId() == StpUtil.getLoginIdAsLong() || permissionOr){
            return bookChapterService.list(
                    Wrappers.lambdaQuery(new BookChapter())
                            .eq(BookChapter::getBookId, bookId)
            );
        }

        return joinPoint.proceed();
    }

}

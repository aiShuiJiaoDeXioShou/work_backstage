package com.yangteng.workbackstage;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.PythonScriptLoad;
import com.yangteng.workbackstage.comm.TableToEntityConstructor;
import com.yangteng.workbackstage.entity.book.Book;
import com.yangteng.workbackstage.entity.book.BookUserReaderChapter;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IBookService;
import com.yangteng.workbackstage.service.IBookUserReaderChapterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WorkBackstageApplicationTests {

    @Autowired
    PythonScriptLoad load;

    @Autowired
    private IBookChapterService chapterService;

    @Autowired
    private IBookUserReaderChapterService readerChapterService;

    @Autowired
    private IBookService bookService;

    @Test
    void contextLoads() {
        List objects = readerChapterService.listObjs(
                Wrappers.lambdaQuery(new BookUserReaderChapter())
                        .select(BookUserReaderChapter::getChapterId)
                        .eq(BookUserReaderChapter::getCreateUser, 1)
        );

        chapterService.listByIds(objects);
    }

    @Test
    public void test2() throws Exception {
        bookService.update(
                Wrappers.lambdaUpdate(new Book())
                        .setSql("clicks = clicks + 1")
                        .eq(Book::getId, 1511915520)
        );
    }

}

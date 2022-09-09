package com.yangteng.workbackstage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangteng.workbackstage.annotation.ChapterJudgeYourSelf;
import com.yangteng.workbackstage.entity.book.BookChapter;
import com.yangteng.workbackstage.entity.book.BookUserReaderChapter;
import com.yangteng.workbackstage.mapper.BookChapterMapper;
import com.yangteng.workbackstage.myenum.MyState;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IBookUserReaderChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-08-12
 */
@Service
public class BookChapterServiceImpl extends ServiceImpl<BookChapterMapper, BookChapter> implements IBookChapterService {

    @Autowired
    private IBookUserReaderChapterService readerChapterService;


    @Override
    // 判断是不是作者本人，如果是作者本人就不用付费了
    @ChapterJudgeYourSelf
    public List<BookChapter> autoChapterList(Long bookId) {
        // 获取这本书所有的免费章节
        List<BookChapter> list = list(
                Wrappers.lambdaQuery(new BookChapter())
                        .eq(BookChapter::getBookId, bookId)
                        .eq(BookChapter::getState, MyState.PutShelves.getState())
        );

        // 判断该用户是否登录,如果登录了，获取用户已解锁的所有章节
        if (StpUtil.isLogin()){
            return list;
        }

        List objects = readerChapterService.listObjs(
                Wrappers.lambdaQuery(new BookUserReaderChapter())
                        .select(BookUserReaderChapter::getChapterId)
                        .eq(BookUserReaderChapter::getCreateUser, StpUtil.getLoginIdAsLong())
        );
        List<BookChapter> paylists = listByIds(objects);
        list.addAll(paylists);
        return list;
    }

}

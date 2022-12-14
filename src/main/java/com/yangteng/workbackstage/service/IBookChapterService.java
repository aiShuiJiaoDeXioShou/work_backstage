package com.yangteng.workbackstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangteng.workbackstage.entity.book.BookChapter;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 林河
 * @since 2022-08-12
 */
public interface IBookChapterService extends IService<BookChapter> {

    List<BookChapter> autoChapterList(Long bookId);

}

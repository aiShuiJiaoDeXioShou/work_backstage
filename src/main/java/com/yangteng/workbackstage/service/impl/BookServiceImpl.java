package com.yangteng.workbackstage.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.entity.book.Book;
import com.yangteng.workbackstage.mapper.BookMapper;
import com.yangteng.workbackstage.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-08-09
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    /**
     * 添加一次点击量
     * @param bookId
     * @return
     */
    @Override
    public boolean addBookClick(Long bookId) {
        return update(
                Wrappers.lambdaUpdate(new Book())
                        .setSql("clicks = clicks + 1")
                        .eq(Book::getId, 1511915520)
        );

    }
}

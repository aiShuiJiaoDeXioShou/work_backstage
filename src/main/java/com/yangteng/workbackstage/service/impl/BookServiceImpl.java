package com.yangteng.workbackstage.service.impl;

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

}

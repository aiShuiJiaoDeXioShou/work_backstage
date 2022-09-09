package com.yangteng.workbackstage.service;

import com.yangteng.workbackstage.entity.book.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林河
 * @since 2022-08-09
 */
public interface IBookService extends IService<Book> {

    /**
     * 获取书籍付费方案,签约书籍
     */


    /**
     * 书籍点击量++
     */
    boolean addBookClick(Long bookId);


}

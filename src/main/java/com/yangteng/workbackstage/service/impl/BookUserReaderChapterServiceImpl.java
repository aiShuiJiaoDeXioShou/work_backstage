package com.yangteng.workbackstage.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.book.BookChapter;
import com.yangteng.workbackstage.entity.book.BookUserReaderChapter;
import com.yangteng.workbackstage.mapper.BookUserReaderChapterMapper;
import com.yangteng.workbackstage.myenum.MyState;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IBookUserReaderChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
@Service
public class BookUserReaderChapterServiceImpl extends ServiceImpl<BookUserReaderChapterMapper, BookUserReaderChapter> implements IBookUserReaderChapterService {

}

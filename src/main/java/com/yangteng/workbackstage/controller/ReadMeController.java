package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.book.BookChapter;
import com.yangteng.workbackstage.entity.dto.ConsumeDto;
import com.yangteng.workbackstage.entity.us.Consume;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IBookUserReaderChapterService;
import com.yangteng.workbackstage.service.IConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 阅读管理
 */
@RestController
@RequestMapping("/reader")
public class ReadMeController {

    @Autowired
    private IBookChapterService chapterService;

    @Autowired
    private IBookUserReaderChapterService readerChapterService;

    @Autowired
    private IConsumeService consumeService;

    /**
     * 获取用户已经得到的章节
     */
    @GetMapping("/{book_id}")
    public R getReaderChapters(@PathVariable Long book_id){
        return R.ok(chapterService.autoChapterList(book_id));
    }


    /**
     * 购买一个章节
     */
    @PostMapping("/{chapter_id}")
    public R goReaderChapter(@PathVariable Long chapter_id) {
        BookChapter nowChapter = chapterService.getById(chapter_id);

        ConsumeDto consumeDto = new ConsumeDto();
        Consume consume = new Consume();

        consume.setMoney(new BigDecimal(0))
                .setBookId(nowChapter.getBookId())
                .setUserId(StpUtil.getLoginIdAsLong())
                .setType(0);

        consumeDto.setConsume(consume);

        consumeService.userConsume(consumeDto);

        return R.ok();
    }



}

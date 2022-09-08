package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangteng.workbackstage.annotation.AuthorHimself;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.book.BookChapter;
import com.yangteng.workbackstage.entity.book.ChapterComment;
import com.yangteng.workbackstage.service.IBookChapterService;
import com.yangteng.workbackstage.service.IChapterCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/chapter")
public class ChapterReadOrWriterManagementController {

    @Autowired
    private IBookChapterService bookChapterService;

    @Autowired
    private IChapterCommentService chapterCommentService;

    /**
     * 获取一本书所有的章节
     *
     * @param bookId
     * @return R.ok(List < BookChapter >) or R.fail
     */
    @GetMapping("/list/{bookId}")
    public R getAllChapter(@PathVariable Integer bookId) {
        final List<BookChapter> books = bookChapterService.list(
                Wrappers.lambdaQuery(new BookChapter())
                        .eq(BookChapter::getBookId, bookId)
                        .orderByDesc(BookChapter::getCreateTime));
        return books != null ? R.ok(books) : R.fail();
    }

    /**
     * 获取一本书所有的章节标题和id
     *
     * @param bookId
     * @return R.ok(List < String >) or R.fail
     */
    @GetMapping("/name/{bookId}")
    public R getAllChapterName(@PathVariable Integer bookId) {

        final LambdaQueryWrapper<BookChapter> bookChapterWrapper = Wrappers.lambdaQuery(new BookChapter())
                .select(BookChapter::getChapterTitle, BookChapter::getId)
                .eq(BookChapter::getBookId, bookId)
                .orderByDesc(BookChapter::getCreateTime);
        final List<Object> chapterNames = bookChapterService.listObjs(bookChapterWrapper);
        return Objects.isNull(chapterNames) ? R.fail() : R.ok(chapterNames);
    }

    /**
     * 分页获取章节
     *
     * @param bookId
     * @param page
     * @param size
     * @return R.ok(Page < BookChapter >) or R.fail
     */
    @GetMapping("/{bookId}/{page}/{size}")
    public R getChapter(@PathVariable Integer bookId, @PathVariable Integer page, @PathVariable Integer size) {
        final LambdaQueryWrapper<BookChapter> bookChapterWrapper = Wrappers.lambdaQuery(new BookChapter())
                .eq(BookChapter::getBookId, bookId)
                .orderByDesc(BookChapter::getCreateTime);
        final Page<BookChapter> chapterPage = bookChapterService.page(new Page<BookChapter>(page, size),
                bookChapterWrapper);
        return Objects.isNull(chapterPage) ? R.fail() : R.ok(chapterPage);
    }

    /**
     * 获取指定章节
     *
     * @param chapterId
     * @return R.ok(BookChapter) or R.fail
     */
    @GetMapping("/{chapterId}")
    public R getChapter(@PathVariable Integer chapterId) {
        final BookChapter chapter = bookChapterService.getById(chapterId);
        return Objects.isNull(chapter) ? R.fail() : R.ok(chapter);
    }

    /**
     * 修改章节
     *
     * @param chapter
     * @return R.ok or R.fail
     */
    @PutMapping
    @AuthorHimself // 只有作者本人和管理员才能更改
    public R updateChapter(@RequestBody BookChapter chapter) {
        final boolean update = bookChapterService.updateById(chapter);
        return update ? R.ok() : R.fail();
    }

    /**
     * 删除指定章节
     *
     * @param chapterId
     */
    @DeleteMapping("/{chapterId}")
    @AuthorHimself
    public R deleteChapter(@PathVariable Integer chapterId) {
        final boolean delete = bookChapterService.remove(
                Wrappers.lambdaQuery(new BookChapter())
                        .eq(BookChapter::getId, chapterId));
        return delete ? R.ok() : R.fail();
    }

    /**
     * 获取指定章节的评论内容
     *
     * @param chapterId
     * @return R.ok(List < String >) or R.fail
     */
    @GetMapping("/comment/{chapterId}")
    @SaCheckLogin
    public R getComment(@PathVariable Integer chapterId) {
        final List<ChapterComment> chapterComments = chapterCommentService.list(
                Wrappers.lambdaQuery(new ChapterComment())
                        .eq(ChapterComment::getChapterId, chapterId)
                        .orderByDesc(ChapterComment::getCreateTime));
        return Objects.isNull(chapterComments) ? R.fail() : R.ok(chapterComments);
    }

    /**
     * 获取指定章节，指定行的评论内容
     *
     * @param chapterId
     * @param number
     * @return R.ok(List < String >) or R.fail
     */
    @GetMapping("/comment/{chapterId}/{number}")
    @SaCheckLogin
    public R getComment(@PathVariable Integer chapterId, @PathVariable Integer number) {
        final List<ChapterComment> chapterComments = chapterCommentService.list(
                Wrappers.lambdaQuery(new ChapterComment())
                        .eq(ChapterComment::getChapterId, chapterId)
                        .eq(ChapterComment::getChapterParagraphNum, number)
                        .orderByDesc(ChapterComment::getCreateTime));
        return Objects.isNull(chapterComments) ? R.fail() : R.ok(chapterComments);
    }

    /**
     * 删除一条评论
     *
     * @param commentId
     * @return R.ok or R.fail
     */
    @DeleteMapping("/comment/{commentId}")
    @SaCheckLogin
    public R deleteComment(@PathVariable Integer commentId) {
        final boolean delete = chapterCommentService.remove(
                Wrappers.lambdaQuery(new ChapterComment())
                        .eq(ChapterComment::getId, commentId));
        return delete ? R.ok() : R.fail();
    }

    /**
     * 新增一本书，只有这本书的作家才能新增这个章节
     * 
     * @param chapter
     * @return R.ok or R.fail
     */
    @AuthorHimself
    @PostMapping
    public R addOneChapter(BookChapter chapter) {
        boolean save = bookChapterService.save(chapter);
        return save ? R.ok() : R.fail();
    }

    /**
     * 扫描一本txt文档或者word文档，转化为书籍
     */
    @SaCheckLogin
    public R uploadBook(BookChapter chapter) {

        return R.ok();
    }



}

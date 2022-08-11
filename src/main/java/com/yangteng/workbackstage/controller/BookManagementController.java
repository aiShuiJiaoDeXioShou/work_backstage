package com.yangteng.workbackstage.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.BookCollect;
import com.yangteng.workbackstage.entity.WorkBook;
import com.yangteng.workbackstage.mapper.BookCollectMapper;
import com.yangteng.workbackstage.myenum.E;
import com.yangteng.workbackstage.service.IBookCollectService;
import com.yangteng.workbackstage.service.IWorkBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 此类包含所有书籍相关的方法
 * 还包过图书的排序
 * 图书的收藏关注以及相应的推荐与搜索算法
 *
 * @author 林河
 * @date 2022/8/12
 */
@RestController
@Slf4j
@RequestMapping("/book")
public class BookManagementController {

    @Autowired
    private IWorkBookService bookService;
    @Autowired
    private IBookCollectService bookCollectService;

    // 查询一本书籍
    @GetMapping("/{id}")
    public R info(@PathVariable Integer id) {

        return R.ok(bookService.getById(id));
    }

    /**
     * 分页查询书籍
     *
     * @param page  分页参数
     * @param limit 每页显示的条数
     * @return R<Page> 返回查询结果
     */
    @GetMapping("/{page}/{limit}")
    public R list(@PathVariable Integer page, @PathVariable Integer limit) {
        Page<WorkBook> pageObj = new Page<WorkBook>(page, limit);
        Page<WorkBook> restult = bookService.page(pageObj);
        return R.ok(restult);
    }

    /**
     * 添加一本图书
     *
     * @param book
     */
    @PostMapping
    public R add(@RequestBody WorkBook book) {
        WorkBook one = bookService.getOne(Wrappers.<WorkBook>lambdaQuery().eq(WorkBook::getName, book.getName()));
        if (one != null) {
            return R.fail("该书籍名称已存在！");
        }
        boolean save = bookService.save(book);
        return save ? R.ok() : R.fail();
    }

    /**
     * 删除一本图书
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id) {
        boolean remove = bookService.removeById(id);
        return remove ? R.ok() : R.fail();
    }

    /**
     * 更新一本图书
     *
     * @param book
     */
    @PutMapping
    public R update(@RequestBody WorkBook book) {
        boolean update = bookService.updateById(book);
        return update ? R.ok() : R.fail();
    }

    /**
     * 分类查询
     *
     * @param type
     * @return R<List<WorkBook>> 返回查询信息
     */
    @GetMapping("/category/{type}")
    public R<List<WorkBook>> category(@PathVariable String type) {
        List<WorkBook> list = bookService.list(new QueryWrapper<WorkBook>().eq("book_category", type));
        return R.ok(list);
    }

    /**
     * 按照点击量查询图书
     *
     * @param page
     * @param limit
     * @return R<Page<WorkBook>> 返回查询信息
     */
    @GetMapping("/click/{page}/{limit}")
    public R<Page<WorkBook>> click(@PathVariable Integer page, @PathVariable Integer limit) {
        Page<WorkBook> pageObj = new Page<WorkBook>(page, limit);
        LambdaQueryWrapper<WorkBook> lmq = Wrappers.lambdaQuery();
        // 按照点击量查询图书,如果点击量相等,则按照时间降序
        lmq.orderByDesc(WorkBook::getClicks).orderByDesc(WorkBook::getUpdateTime);
        Page<WorkBook> restult = bookService.page(pageObj, lmq);
        return R.ok(restult);
    }

    /**
     * 更新点击量
     *
     * @param id
     */
    @PutMapping("/click/{id}")
    public R updateClick(@PathVariable Integer id) {
        WorkBook book = bookService.getById(id);
        book.setClicks(book.getClicks() + 1);
        boolean update = bookService.updateById(book);
        return update ? R.ok() : R.fail();
    }

    /**
     * 按照收藏量和分类条件查询图书
     *
     * @return R 返回查询信息
     */
    @GetMapping("/collect/{page}/{limit}")
    public R toCollect(
            @PathVariable Integer page,
            @PathVariable Integer limit) {

        final BookCollectMapper bookMapper = (BookCollectMapper) bookCollectService.getBaseMapper();
        final List<HashMap> list = bookMapper.selectBookCollectCountByBookId(0, 10);
        log.info("收藏个数{}", list);

        LambdaQueryWrapper<WorkBook> lmb = Wrappers.lambdaQuery();
        Page<WorkBook> pageObj = new Page<WorkBook>(page, limit);
        Page<WorkBook> restult = bookService.page(pageObj, lmb);
        return R.ok(restult);
    }

    /**
     * 按照分类条件和月票查询图书
     *
     * @param type
     * @param page
     * @param limit
     * @return R<Page<WorkBook>> 返回查询信息
     */
    @GetMapping("/ticket/{type}/{page}/{limit}")
    public R<Page<WorkBook>> toTicket(@PathVariable Integer type, @PathVariable Integer page,
                                      @PathVariable(value = "10") Integer limit) {

        return null;
    }

    /**
     * 新增一个收藏
     *
     * @param bookId 书籍id
     */
    @PostMapping("/collect/{bookId}")
    public R addCollect(@PathVariable Integer bookId) {
        BookCollect bookCollect = new BookCollect();
        bookCollect.setBookId(bookId);
        if (!StpUtil.isLogin()) {
            return new R("你还未登入", E.NOT_LOGIN);
        }
        bookCollect.setUserId(StpUtil.getLoginIdAsInt());
        return bookCollectService.save(bookCollect) ? R.ok() : R.fail();
    }

    /**
     * 取消收藏
     *
     * @param bookId 书籍id
     */
    @DeleteMapping("/collect/{bookId}")
    public R deleted(@PathVariable Integer bookId) {
        if (!StpUtil.isLogin()) {
            return new R("你还未登入", E.NOT_LOGIN);
        }
        LambdaQueryWrapper<BookCollect> lambdaQuery = Wrappers.lambdaQuery(new BookCollect());
        lambdaQuery.eq(BookCollect::getBookId, bookId).eq(BookCollect::getUserId, StpUtil.getLoginIdAsInt());
        return bookCollectService.remove(lambdaQuery) ? R.ok() : R.fail();
    }
}

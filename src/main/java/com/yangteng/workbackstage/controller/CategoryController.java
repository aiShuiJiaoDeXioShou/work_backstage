package com.yangteng.workbackstage.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangteng.workbackstage.comm.R;
import com.yangteng.workbackstage.entity.book.Book;
import com.yangteng.workbackstage.entity.book.BookCategory;
import com.yangteng.workbackstage.myenum.authority.Level;
import com.yangteng.workbackstage.service.IBookCategoryService;
import com.yangteng.workbackstage.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private IBookCategoryService categoryService;

    /**
     * 分类查询
     *
     * @param type
     * @return R<List<WorkBook>> 返回查询信息
     */
    @GetMapping("/{type}")
    public R category(@PathVariable String type) {
        // 查询有关书籍
        List<Book> list = bookService.list(new QueryWrapper<Book>().eq("book_category", type));

        return R.ok(list);
    }

    /**
     * 获取类别信息
     */
    @GetMapping
    public R getCategory() {
        // 根据每个类别里面的封面图书，获取封面图书的信息
        List<BookCategory> list = categoryService.list(
                Wrappers.lambdaQuery(new BookCategory())
                        .eq(BookCategory::getChrId, Level.Highest.getCode())
        );
        List<Object> returnValue = list.stream()
                .map(BeanUtil::beanToMap)
                .map(map -> map.put("book", bookService.getById((Long) map.get("baseBookId"))))
                .collect(Collectors.toList());

        return R.ok(returnValue);
    }
}

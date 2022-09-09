package com.yangteng.workbackstage.myenum.level;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 经验增长类型
 */
@AllArgsConstructor
@Getter
public enum ExRecordType {

    Read(0, "阅读"),Book(1, "书籍"),Author(2, "作者");

    @EnumValue
    private Integer type;
    private String title;

}

package com.yangteng.workbackstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 林河
 * @since 2022-08-12
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("book_chapter")
@ApiModel(value = "BookChapter对象", description = "")
public class BookChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("书籍id")
    @TableField("book_id")
    private Integer bookId;

    @ApiModelProperty("创建者的用户id")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;

    @ApiModelProperty("更新者的用户id")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;

    @ApiModelProperty("章节的标题，max25")
    @TableField("chapter_title")
    private String chapterTitle;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("章节的字数")
    @TableField("num")
    private Long num;
}

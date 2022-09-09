package com.yangteng.workbackstage.entity.book;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林河
 * @since 2022-09-08
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("book_user_reader_chapter")
@ApiModel(value = "BookUserReaderChapter对象", description = "")
public class BookUserReaderChapter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户章节付费id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("书籍id")
    @TableField("book_id")
    private Long bookId;

    @ApiModelProperty("创建者的用户id")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("章节id")
    @TableField("chapter_id")
    private Long chapterId;

    @ApiModelProperty("本章消费的金额")
    @TableField("money")
    private BigDecimal money;

    @ApiModelProperty("0为章节消费，1为书籍消费")
    @TableField("type")
    private Byte type;
}

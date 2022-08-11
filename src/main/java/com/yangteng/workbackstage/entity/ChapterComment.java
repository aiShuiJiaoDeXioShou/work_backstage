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
@TableName("chapter_comment")
@ApiModel(value = "ChapterComment对象", description = "")
public class ChapterComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节评论id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("创建者的用户id")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;

    @ApiModelProperty("评论的创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("章节id")
    @TableField("chapter_id")
    private Integer chapterId;

    @ApiModelProperty("被评论章节的段落编号")
    @TableField("chapter_paragraph_num")
    private Integer chapterParagraphNum;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("被评论对象的id，默认为零的情况下，则是顶楼")
    @TableField("be_id")
    private Integer beId;
}

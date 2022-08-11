package com.yangteng.workbackstage.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 林河
 * @since 2022-08-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("work_book")
@ApiModel(value = "WorkBook对象", description = "")
public class WorkBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("书籍id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除字段")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("书籍名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("作者id")
    @TableField("autor_id")
    private Integer autorId;

    @ApiModelProperty("书籍套餐id")
    @TableField("price_combo_id")
    private Integer priceComboId;

    @ApiModelProperty("指定书籍价格，有它在price_combo_id不起效")
    @TableField("book_price")
    private BigDecimal bookPrice;

    @ApiModelProperty("书籍的封面")
    @TableField("book_image_url")
    private String bookImageUrl;

    @ApiModelProperty("书籍的简介")
    @TableField("book_introduce")
    private String bookIntroduce;

    @ApiModelProperty("书籍类别")
    @TableField("book_category")
    private String bookCategory;

    @ApiModelProperty("被点击量")
    @TableField("clicks")
    private Integer clicks;

    @ApiModelProperty("书籍状态0表示未通过审查,1表示已通过审查,2表示已签约,3表示已上架,-1表示已封杀")
    @TableField("state")
    private Integer state;
}

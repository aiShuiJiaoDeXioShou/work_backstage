package com.yangteng.workbackstage.entity.book;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("book")
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("书籍id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

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
    private Long autorId;

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

package com.yangteng.workbackstage.entity.us;

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
@TableName("us_consume")
@ApiModel(value = "Consume对象", description = "")
public class Consume implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("信息id")
    @TableField("book_id")
    private Long bookId;

    @ApiModelProperty("效费版本")
    @TableField("version")
    private Integer version;

    @ApiModelProperty("消费金额")
    @TableField("money")
    private BigDecimal money;

    @ApiModelProperty("消费类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("逻辑删除字段")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("该消费的说明")
    @TableField("illustrate")
    private String illustrate;
}

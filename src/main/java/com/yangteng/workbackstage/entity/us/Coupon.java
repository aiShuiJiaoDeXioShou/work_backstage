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
@TableName("us_coupon")
@ApiModel(value = "Coupon对象", description = "")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账单记录")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者的用户id")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("更新者的用户id")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("优惠卷名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("优惠金额")
    @TableField("discounted_price")
    private BigDecimal discountedPrice;

    @ApiModelProperty("优惠力度max10，如果没有折扣金额，就执行这个")
    @TableField("discount")
    private Integer discount;

    @ApiModelProperty("过期时间，过了过期时间就不能用了")
    @TableField("expired_time")
    private LocalDateTime expiredTime;

    @ApiModelProperty("逻辑删除字段")
    @TableField("deleted")
    @TableLogic
    private Integer deleted;
}

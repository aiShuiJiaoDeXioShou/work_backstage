package com.yangteng.workbackstage.entity.us;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.yangteng.workbackstage.myenum.level.WordtreeAuthorLevel;
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
 * @since 2022-09-09
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("work_user")
@ApiModel(value = "WorkUser对象", description = "")
public class WorkUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户名是唯一的，最大值为12位字符")
    @TableField("name")
    private String name;

    @ApiModelProperty("密码最大为12位字符")
    @TableField("password")
    private String password;

    @ApiModelProperty("电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("手机号码")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("账户余额")
    @TableField("account_balance")
    private BigDecimal accountBalance;

    @ApiModelProperty("阅读等级")
    @TableField("read_level")
    private WordtreeAuthorLevel readLevel;

    @ApiModelProperty("作者等级")
    @TableField("author_level")
    private WordtreeAuthorLevel authorLevel;

    @ApiModelProperty("作者经验")
    @TableField("author_ex")
    private Long authorEx;

    @ApiModelProperty("阅读经验")
    @TableField("read_ex")
    private Long readEx;
}

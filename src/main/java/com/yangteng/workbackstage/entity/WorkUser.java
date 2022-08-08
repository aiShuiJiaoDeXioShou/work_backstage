package com.yangteng.workbackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林河
 * @since 2022-08-06
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("work_user")
@ApiModel(value = "WorkUser对象", description = "")
public class WorkUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名是唯一的，最大值为12位字符")
    private String name;

    @ApiModelProperty("密码最大为12位字符")
    private String password;

    @ApiModelProperty("创造时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("账户余额")
    private Object accountBalance;
}

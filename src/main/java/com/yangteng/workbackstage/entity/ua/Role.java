package com.yangteng.workbackstage.entity.ua;

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
 * @since 2022-08-11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("ua_role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者的用户id")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;

    @ApiModelProperty("更新者的用户id")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;

    @ApiModelProperty("角色名称限制25个字符")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("角色详情，他的应用场景")
    @TableField("role_info")
    private String roleInfo;
}

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
@TableName("ua_role_for_authority")
@ApiModel(value = "RoleForAuthority对象", description = "")
public class RoleForAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色to权限id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("创造时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("权限id")
    @TableField("authority_id")
    private Integer authorityId;
}

package com.hyggegood.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与权限关联表
 * </p>
 *
 * @author HyggeGood-YangDengYu
 * @since 2024-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_roles_permissions")
public class RolesPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色权限ID
     */
    @TableId(value = "role_permission_id", type = IdType.AUTO)
    private Integer rolePermissionId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 权限ID
     */
    @TableField("permission")
    private String permission;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;


}

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
 * 用户表
 * </p>
 *
 * @author HyggeGood-YangDengYu
 * @since 2024-11-21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID（自增）
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码（通常加密存储）
     */
    @TableField("password")
    private String password;

    /**
     * 手机号（可选）
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户角色
     */
    @TableField("role")
    private Byte role;

    /**
     * 账号状态
     */
    @TableField("state")
    private Byte state;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}

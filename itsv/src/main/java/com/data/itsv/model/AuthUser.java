package com.data.itsv.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
@Table(name = "auth_user")
@Data
public class AuthUser implements Serializable {
    /**
     * 主键
     */
    @Id
    private Integer id;
    /**
     * 用户标识
     */
    private String code;
    /**
     * 用户级别
     */
    private String userLevel;


    /**
     * 用户名(nick_name)
     */
    private String username;

    /**
     * 密码(MD5(密码+盐))
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 用户真名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话号码(唯一)
     */
    private String phone;

    /**
     * 邮件地址(唯一)
     */
    private String email;

    /**
     * 性别(1.男 2.女)
     */
    private int sex;

    /**
     * 账户状态(1.正常 2.锁定 3.删除 4.非法)
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建来源(1.web 2.android 3.ios 4.win 5.macos 6.ubuntu)
     */
    private int createWhere;
    /**
     * 创建来源(1.web 2.android 3.ios 4.win 5.macos 6.ubuntu)
     */
    @Transient
    private String roleId;
    /**
     * 角色 菜单权限
     */
    @Transient
    private String roleMenuPrivilege;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table auth_user
     *
     * @mbg.generated Thu Dec 17 09:49:03 CST 2020
     */
    private static final long serialVersionUID = 1L;

}
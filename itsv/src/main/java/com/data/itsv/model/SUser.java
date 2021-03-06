package com.data.itsv.model;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
@Data
public class SUser implements Serializable {
    /**
     * 用户编号
     */
    private String code;

    /**
     * 部门编号
     */
    private Integer orgId;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 角色编号
     */
    private Integer jobId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机
     */
    private String phone;

    /**
     * 座机
     */
    private String telPhone;

    /**
     * 在线状态;1在线，0：离线
     */
    private Integer loginState;

    /**
     * 上线时间
     */
    private Date loginTime;

    /**
     * 下线时间
     */
    private Date logoutTime;

    /**
     * 用户等级：1、2、3、4、5等5个等级，级别一次升高
     */
    private Integer leveli;

    /**
     * 用户启用状态,0未启用，1启用
     */
    private Integer activeState;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 
     */
    private Date createtime;

    /**
     * 用户登录时间启用标志。0未启用，1启用
     */
    private Integer timeLimitState;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 登陆系统次数。首次登陆必须更改密码
     */
    private Integer loginCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_user
     *
     * @mbg.generated Mon Dec 28 10:50:39 CST 2020
     */
    private static final long serialVersionUID = 1L;


    @Transient
    private Date LastHeartBeatTime;
    @Transient
    private VServer mompServer;

    @Transient
    private String cmsIP;

    @Transient
    private  String cmsPort;
    @Transient
    private String funcPrivilege;
}
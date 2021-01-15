package com.data.itsv.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "auth_role")
@Data
public class AuthRole implements Serializable {
    /**
     * 角色ID
     */
    @Id
    private Integer id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态   1:正常、9：禁用
     */
    private Short status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table auth_role
     *
     * @mbg.generated Thu Dec 17 09:48:24 CST 2020
     */
    private static final long serialVersionUID = 1L;


}
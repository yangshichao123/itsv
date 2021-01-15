package com.data.itsv.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "auth_resource")
@Data
public class AuthResource implements Serializable {
    /**
     * 资源ID
     */
    @Id
    private Integer id;

    /**
     * 资源名称
     */
    private String code;

    /**
     * 资源描述
     */
    private String name;

    /**
     * 父资源编码->菜单
     */
    private Integer parentId;

    /**
     * 访问地址URL
     */
    private String uri;

    /**
     * 类型 1:菜单menu 2:资源element(rest-api) 3:资源分类
     */
    private Short type;

    /**
     * 访问方式 GET POST PUT DELETE PATCH
     */
    private String method;

    /**
     * 图标
     */
    private String icon;

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
     * This field corresponds to the database table auth_resource
     *
     * @mbg.generated Thu Dec 17 09:48:00 CST 2020
     */
    private static final long serialVersionUID = 1L;


}
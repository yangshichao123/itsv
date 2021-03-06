package com.data.itsv.model;

import java.io.Serializable;

public class VFactory implements Serializable {
    /**
     * 厂商编号
     */
    private String code;

    /**
     * 序号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 联系人
     */
    private String contactUser;

    /**
     * 联系人电话
     */
    private String contactUserTelno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_factory
     *
     * @mbg.generated Mon Dec 28 10:55:16 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser == null ? null : contactUser.trim();
    }

    public String getContactUserTelno() {
        return contactUserTelno;
    }

    public void setContactUserTelno(String contactUserTelno) {
        this.contactUserTelno = contactUserTelno == null ? null : contactUserTelno.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", contactUser=").append(contactUser);
        sb.append(", contactUserTelno=").append(contactUserTelno);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
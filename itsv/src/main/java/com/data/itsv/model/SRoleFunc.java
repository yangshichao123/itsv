package com.data.itsv.model;

import java.io.Serializable;

public class SRoleFunc implements Serializable {
    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 角色模块权限，自定义权限字符串，0表示未有权限，1表示有权限，如：0101010
     */
    private String privilege;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_func
     *
     * @mbg.generated Mon Dec 28 10:49:42 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege == null ? null : privilege.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", privilege=").append(privilege);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
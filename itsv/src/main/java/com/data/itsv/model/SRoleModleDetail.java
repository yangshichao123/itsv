package com.data.itsv.model;

import java.io.Serializable;

public class SRoleModleDetail implements Serializable {
    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 模块编号
     */
    private Integer modleCode;

    /**
     * 权限
     */
    private String privilege;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_modle_detail
     *
     * @mbg.generated Mon Dec 28 10:49:52 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModleCode() {
        return modleCode;
    }

    public void setModleCode(Integer modleCode) {
        this.modleCode = modleCode;
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
        sb.append(", modleCode=").append(modleCode);
        sb.append(", privilege=").append(privilege);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
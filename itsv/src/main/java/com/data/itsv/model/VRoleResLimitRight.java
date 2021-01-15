package com.data.itsv.model;

import java.io.Serializable;

public class VRoleResLimitRight implements Serializable {
    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 资源编号
     */
    private String resourceCode;

    /**
     * 通道类型,，1视频通道，2语音通道，3告警通道
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_role_res_limit_right
     *
     * @mbg.generated Mon Dec 28 11:00:10 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode == null ? null : resourceCode.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceCode=").append(resourceCode);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
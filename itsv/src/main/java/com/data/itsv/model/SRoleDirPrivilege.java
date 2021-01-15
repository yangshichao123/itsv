package com.data.itsv.model;

import java.io.Serializable;

public class SRoleDirPrivilege implements Serializable {
    /**
     * 编号
     */
    private Integer roleId;

    /**
     * 目录编号
     */
    private Integer dirId;

    /**
     * 视频资源权限该字段为二进制转化成十进制进行权限的存储
     */
    private String resPrivilege;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_role_dir_privilege
     *
     * @mbg.generated Mon Dec 28 10:49:31 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDirId() {
        return dirId;
    }

    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    public String getResPrivilege() {
        return resPrivilege;
    }

    public void setResPrivilege(String resPrivilege) {
        this.resPrivilege = resPrivilege == null ? null : resPrivilege.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", dirId=").append(dirId);
        sb.append(", resPrivilege=").append(resPrivilege);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.data.itsv.model;

import java.io.Serializable;
import java.util.Date;

public class VIgroup implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;

    /**
     * 目录编号
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 目录等级
     */
    private Integer dirLevel;

    /**
     * 完整路径
     */
    private String fullPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_igroup
     *
     * @mbg.generated Mon Dec 28 10:55:59 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDirLevel() {
        return dirLevel;
    }

    public void setDirLevel(Integer dirLevel) {
        this.dirLevel = dirLevel;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath == null ? null : fullPath.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", name=").append(name);
        sb.append(", descs=").append(descs);
        sb.append(", parentId=").append(parentId);
        sb.append(", createTime=").append(createTime);
        sb.append(", dirLevel=").append(dirLevel);
        sb.append(", fullPath=").append(fullPath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
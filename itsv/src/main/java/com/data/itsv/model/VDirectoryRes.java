package com.data.itsv.model;

import java.io.Serializable;

public class VDirectoryRes implements Serializable {
    /**
     * 视频输入通道编号
     */
    private String resCode;

    /**
     * 目录编号
     */
    private Integer directoryId;

    /**
     * 通道类型,，1视频通道，2语音通道，3告警通道
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_directory_res
     *
     * @mbg.generated Mon Dec 28 10:54:10 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public Integer getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(Integer directoryId) {
        this.directoryId = directoryId;
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
        sb.append(", resCode=").append(resCode);
        sb.append(", directoryId=").append(directoryId);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
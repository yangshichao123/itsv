package com.data.itsv.model;

import java.io.Serializable;

public class VEvssPlan implements Serializable {
    /**
     * 摄像机编号
     */
    private String videoCode;

    /**
     * 存储服务编号
     */
    private String evssServerCode;

    /**
     * 预录时长，单位秒
     */
    private Integer prerecordTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_evss_plan
     *
     * @mbg.generated Mon Dec 28 10:55:03 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode == null ? null : videoCode.trim();
    }

    public String getEvssServerCode() {
        return evssServerCode;
    }

    public void setEvssServerCode(String evssServerCode) {
        this.evssServerCode = evssServerCode == null ? null : evssServerCode.trim();
    }

    public Integer getPrerecordTime() {
        return prerecordTime;
    }

    public void setPrerecordTime(Integer prerecordTime) {
        this.prerecordTime = prerecordTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoCode=").append(videoCode);
        sb.append(", evssServerCode=").append(evssServerCode);
        sb.append(", prerecordTime=").append(prerecordTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
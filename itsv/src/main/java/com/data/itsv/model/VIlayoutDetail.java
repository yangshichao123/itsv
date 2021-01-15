package com.data.itsv.model;

import java.io.Serializable;

public class VIlayoutDetail implements Serializable {
    /**
     * 视频输入通道编号
     */
    private String videoCode;

    /**
     * 布局编号
     */
    private Integer layoutId;

    /**
     * 位置编号
     */
    private Integer locationNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_ilayout_detail
     *
     * @mbg.generated Mon Dec 28 10:56:31 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode == null ? null : videoCode.trim();
    }

    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public Integer getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(Integer locationNum) {
        this.locationNum = locationNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoCode=").append(videoCode);
        sb.append(", layoutId=").append(layoutId);
        sb.append(", locationNum=").append(locationNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
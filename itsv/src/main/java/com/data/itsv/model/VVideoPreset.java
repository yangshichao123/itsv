package com.data.itsv.model;

import java.io.Serializable;

public class VVideoPreset implements Serializable {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 守望位标志0不是守望位1守望位
     */
    private Integer keepWatchFlag;

    /**
     * 摄像机编号
     */
    private String videoCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 摄像机索引位 
     */
    private Integer indexNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_video_preset
     *
     * @mbg.generated Mon Dec 28 11:03:12 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKeepWatchFlag() {
        return keepWatchFlag;
    }

    public void setKeepWatchFlag(Integer keepWatchFlag) {
        this.keepWatchFlag = keepWatchFlag;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode == null ? null : videoCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keepWatchFlag=").append(keepWatchFlag);
        sb.append(", videoCode=").append(videoCode);
        sb.append(", name=").append(name);
        sb.append(", indexNum=").append(indexNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
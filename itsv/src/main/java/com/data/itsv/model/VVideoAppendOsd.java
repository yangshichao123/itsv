package com.data.itsv.model;

import java.io.Serializable;

public class VVideoAppendOsd implements Serializable {
    /**
     * 视频通道编号
     */
    private String videoCode;

    /**
     * x坐标
     */
    private Integer x;

    /**
     * y坐标
     */
    private Integer y;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否隐藏标志 0显示，1隐藏
     */
    private Integer enableHide;

    /**
     * 位置编号
     */
    private Integer pos;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_video_append_osd
     *
     * @mbg.generated Mon Dec 28 11:03:03 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode == null ? null : videoCode.trim();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getEnableHide() {
        return enableHide;
    }

    public void setEnableHide(Integer enableHide) {
        this.enableHide = enableHide;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoCode=").append(videoCode);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", content=").append(content);
        sb.append(", enableHide=").append(enableHide);
        sb.append(", pos=").append(pos);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
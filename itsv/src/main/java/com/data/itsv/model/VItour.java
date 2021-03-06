package com.data.itsv.model;

import java.io.Serializable;

public class VItour implements Serializable {
    /**
     * 巡视组编号
     */
    private Integer code;

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
     * 时间间隔
     */
    private Integer interval;

    /**
     * 屏幕数1 
     */
    private Integer screen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_itour
     *
     * @mbg.generated Mon Dec 28 10:56:42 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getScreen() {
        return screen;
    }

    public void setScreen(Integer screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", userCode=").append(userCode);
        sb.append(", name=").append(name);
        sb.append(", descs=").append(descs);
        sb.append(", interval=").append(interval);
        sb.append(", screen=").append(screen);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
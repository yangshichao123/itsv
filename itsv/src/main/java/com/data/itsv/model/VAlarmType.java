package com.data.itsv.model;

import java.io.Serializable;

public class VAlarmType implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 系统分类编号。视频监控系统-0，通信电源及环境监控系统-1，牵引供电系统-2，旅客服务系统-3，自然灾害及异物侵限监测系统-4
     */
    private Integer systemType;

    /**
     * 告警主类型
     */
    private String alarmMainType;

    /**
     * 告警子类型
     */
    private String alarmDetailType;

    /**
     * 告警类型
     */
    private String alarmType;

    /**
     * 名称
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_alarm_type
     *
     * @mbg.generated Mon Dec 28 10:52:43 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getAlarmMainType() {
        return alarmMainType;
    }

    public void setAlarmMainType(String alarmMainType) {
        this.alarmMainType = alarmMainType == null ? null : alarmMainType.trim();
    }

    public String getAlarmDetailType() {
        return alarmDetailType;
    }

    public void setAlarmDetailType(String alarmDetailType) {
        this.alarmDetailType = alarmDetailType == null ? null : alarmDetailType.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", systemType=").append(systemType);
        sb.append(", alarmMainType=").append(alarmMainType);
        sb.append(", alarmDetailType=").append(alarmDetailType);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.data.itsv.model;

import java.io.Serializable;

public class VResMap implements Serializable {
    /**
     * 自有id，用户不可编辑
     */
    private Integer objectid;

    /**
     * 唯一id 
     */
    private String code;

    /**
     * 资源类型，1服务资源，2设备资源，3视频通道,123  基站设备 ,124  定位井盖设备,  125  modbus设备 ,  126  寻址井盖设备,  127  人员定位点设备,128泵站
     */
    private Integer resType;

    /**
     * sde属性标示点线面类型
     */
    private String shape;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_res_map
     *
     * @mbg.generated Mon Dec 28 10:59:38 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape == null ? null : shape.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", objectid=").append(objectid);
        sb.append(", code=").append(code);
        sb.append(", resType=").append(resType);
        sb.append(", shape=").append(shape);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
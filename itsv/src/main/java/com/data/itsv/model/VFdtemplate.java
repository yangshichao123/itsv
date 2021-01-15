package com.data.itsv.model;

import java.io.Serializable;

public class VFdtemplate implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 软件版本
     */
    private String swversion;

    /**
     * 硬件版本
     */
    private String hdversion;

    /**
     * 设备带宽(M)
     */
    private Integer bandwidth;

    /**
     * 视频输入通道数
     */
    private Integer videoinNum;

    /**
     * 音频输入通道数
     */
    private Integer audioinNum;

    /**
     * 语音输入通道数
     */
    private String phonicnum;

    /**
     * 报警输入通道数
     */
    private Integer alarminNum;

    /**
     * 描述
     */
    private String descs;

    /**
     * 厂商编号
     */
    private String factoryCode;

    /**
     * 设备类型编号
     */
    private String fdtypeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_fdtemplate
     *
     * @mbg.generated Mon Dec 28 10:55:50 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSwversion() {
        return swversion;
    }

    public void setSwversion(String swversion) {
        this.swversion = swversion == null ? null : swversion.trim();
    }

    public String getHdversion() {
        return hdversion;
    }

    public void setHdversion(String hdversion) {
        this.hdversion = hdversion == null ? null : hdversion.trim();
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Integer getVideoinNum() {
        return videoinNum;
    }

    public void setVideoinNum(Integer videoinNum) {
        this.videoinNum = videoinNum;
    }

    public Integer getAudioinNum() {
        return audioinNum;
    }

    public void setAudioinNum(Integer audioinNum) {
        this.audioinNum = audioinNum;
    }

    public String getPhonicnum() {
        return phonicnum;
    }

    public void setPhonicnum(String phonicnum) {
        this.phonicnum = phonicnum == null ? null : phonicnum.trim();
    }

    public Integer getAlarminNum() {
        return alarminNum;
    }

    public void setAlarminNum(Integer alarminNum) {
        this.alarminNum = alarminNum;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode == null ? null : factoryCode.trim();
    }

    public String getFdtypeCode() {
        return fdtypeCode;
    }

    public void setFdtypeCode(String fdtypeCode) {
        this.fdtypeCode = fdtypeCode == null ? null : fdtypeCode.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", swversion=").append(swversion);
        sb.append(", hdversion=").append(hdversion);
        sb.append(", bandwidth=").append(bandwidth);
        sb.append(", videoinNum=").append(videoinNum);
        sb.append(", audioinNum=").append(audioinNum);
        sb.append(", phonicnum=").append(phonicnum);
        sb.append(", alarminNum=").append(alarminNum);
        sb.append(", descs=").append(descs);
        sb.append(", factoryCode=").append(factoryCode);
        sb.append(", fdtypeCode=").append(fdtypeCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
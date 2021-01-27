package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class FDTemplateModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author madi 说明：软件版本
	 */
	private String SWVersion;
	/**
	 * @author madi 说明：硬件版本
	 */
	private String HDVersion;
	/**
	 * @author madi 说明：设备带宽(M)
	 */
	private String BandWidth;

	/**
	 * @author madi 说明：视频输入通道数
	 */
	private String videoinNum;
	/**
	 * @author madi 说明：音频输入通道数
	 */
	private String audioinNum;
	/**
	 * @author madi 说明：语音输入通道数
	 */
	private String phonicNum;
	/**
	 * @author madi 说明：报警输入通道数
	 */
	private String alarminNum;
	/**
	 * @author madi 说明：厂商编号
	 */
	private String factoryCode;
	/**
	 * @author madi 说明：厂商名称
	 */
	private String factoryName;
	/**
	 * @author madi 说明：设备类型编号
	 */
	private String fdTypeCode;
	/**
	 * @author madi 说明：设备类型名称
	 */
	private String fdTypeName;
	/**
	 * @author madi
	 * @说明：批量添加数量
	 */
	private String num;

	public String getSWVersion() {
		return SWVersion;
	}

	public void setSWVersion(String sWVersion) {
		SWVersion = sWVersion;
	}

	public String getHDVersion() {
		return HDVersion;
	}

	public void setHDVersion(String hDVersion) {
		HDVersion = hDVersion;
	}

	public String getBandWidth() {
		return BandWidth;
	}

	public void setBandWidth(String bandWidth) {
		BandWidth = bandWidth;
	}

	public String getVideoinNum() {
		return videoinNum;
	}

	public void setVideoinNum(String videoinNum) {
		this.videoinNum = videoinNum;
	}

	public String getAudioinNum() {
		return audioinNum;
	}

	public void setAudioinNum(String audioinNum) {
		this.audioinNum = audioinNum;
	}

	public String getPhonicNum() {
		return phonicNum;
	}

	public void setPhonicNum(String phonicNum) {
		this.phonicNum = phonicNum;
	}

	public String getAlarminNum() {
		return alarminNum;
	}

	public void setAlarminNum(String alarminNum) {
		this.alarminNum = alarminNum;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFdTypeCode() {
		return fdTypeCode;
	}

	public void setFdTypeCode(String fdTypeCode) {
		this.fdTypeCode = fdTypeCode;
	}

	public String getFdTypeName() {
		return fdTypeName;
	}

	public void setFdTypeName(String fdTypeName) {
		this.fdTypeName = fdTypeName;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}

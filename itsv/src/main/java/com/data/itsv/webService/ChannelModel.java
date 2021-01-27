package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @author madi
 * @说明：通道
 */
public class ChannelModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author madi
	 * @说明：编号
	 */
	private String code;
	/**
	 * @author madi
	 * @说明:类型
	 */
	private String type;
	
	private String types;
	/**
	 * @author madi
	 * @说明：设备编号
	 */
	private String fdCode;
	/**
	 * @author madi
	 * @说明：存储计划编号
	 */
	private String planId;
	/**
	 * @author madi
	 * @说明：扩展字段
	 */
	private String ext;
	
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getFdCode() {
		return fdCode;
	}
	public void setFdCode(String fdCode) {
		this.fdCode = fdCode;
	}
	
	private String state;
	
	private String ptzFlag;
	
	private String voiceId;
	
	private String realCodeType;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPtzFlag() {
		return ptzFlag;
	}
	public void setPtzFlag(String ptzFlag) {
		this.ptzFlag = ptzFlag;
	}
	public String getVoiceId() {
		return voiceId;
	}
	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}
	public void setRealCodeType(String realCodeType) {
		this.realCodeType = realCodeType;
	}
	public String getRealCodeType() {
		return realCodeType;
	}
	
	
	
}

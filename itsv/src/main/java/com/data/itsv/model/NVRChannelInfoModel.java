package com.data.itsv.model;

import com.data.itsv.model.vo.VFdeviceVo;

/**
 * NVR设备通道信息
 * @author gaohe
 * */
public class NVRChannelInfoModel extends VFdeviceVo {
	/**
	 * 所在设备通道号
	 * @author gaohe
	 * */
	private int channelNum;
	/**
	 * 所属设备编号
	 * @author gaohe
	 * */
	private String parentFdCode;
	/**
	 * 所属设备通道编号
	 * @author gaohe
	 * */
	private String channelCode;
	
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public int getChannelNum() {
		return channelNum;
	}
	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}
	public String getParentFdCode() {
		return parentFdCode;
	}
	public void setParentFdCode(String parentFdCode) {
		this.parentFdCode = parentFdCode;
	}
	
}

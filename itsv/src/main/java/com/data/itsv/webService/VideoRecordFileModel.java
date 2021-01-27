package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * 录像文件信息
 * @author gaohe
 * */
public class VideoRecordFileModel extends BaseModel {
	/**
	 * 摄像机编号
	 * @author gaohe
	 * */
	private String videoCode;
	/**
	 * sn号 唯一编号
	 * @author gaohe
	 * */
	private String sn;
	public String getVideoCode() {
		return videoCode;
	}
	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
}

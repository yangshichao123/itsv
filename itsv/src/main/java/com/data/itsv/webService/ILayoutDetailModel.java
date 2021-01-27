package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @author wym
 * @说明：我的布局細節類
 * **/
public class ILayoutDetailModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author wym
	 * @说明：布局编号
	 * **/
	private String iLayoutId;
	/**
	 * @author wym
	 * @说明：资源编号
	 * **/
	private String videoCode;
	/**
	 * @author wym
	 * @说明：资源编号
	 * **/
	private String locationNum;
	public String getiLayoutId() {
		return iLayoutId;
	}
	public void setiLayoutId(String iLayoutId) {
		this.iLayoutId = iLayoutId;
	}
	public String getVideoCode() {
		return videoCode;
	}
	public void setVideoCode(String videoCode) {
		this.videoCode = videoCode;
	}
	public String getLocationNum() {
		return locationNum;
	}
	public void setLocationNum(String locationNum) {
		this.locationNum = locationNum;
	}
}

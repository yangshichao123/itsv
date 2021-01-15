package com.data.itsv.model;

/**
 * @author ghj
 * @说明：AMQ上报上来的摄像机状态信息。
 * */
public class AMQDeviceStateModel {
	/**
	 * @author ghj
	 * @说明：镜头资源ID
	 * */
	private String resId;
	/**
	 * @author ghj
	 * @说明：online表示摄像机有图，offline表示摄像机无图像
	 * */
	private String stateS, stateI;
	/**
	 * 时间
	 * @author ghj
	 * */
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getStateS() {
		return stateS;
	}

	public void setStateS(String stateS) {
		this.stateS = stateS;
	}

	public String getStateI() {
		return stateI;
	}

	public void setStateI(String stateI) {
		this.stateI = stateI;
	}
}

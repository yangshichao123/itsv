package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * 摄像机存储计划信息
 * 
 * @author gaohe
 * */
public class VideoStorePlanModel extends BaseModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 摄像机是否有存储计划。0:无，1有
	 * 
	 * @author gaohe
	 * */
	private String exsitStorePlanFlag, exsitStorePlanFlags;
	/**
	 * 摄像机是否有存储计划执行状态。1：未开始、2:开始、3:结束
	 * 
	 * @author gaohe
	 * */
	private String storePlanExeState, storePlanExeStates;
	/**
	 * 摄像机存储状态。1：正常、2:断流
	 * 
	 * @author gaohe
	 * */
	private String storeState, storeStates;
	/**
	 * 存储状态断流描述。
	 * 
	 * @author gaohe
	 * */
	private String storeState4NoStreamDesc;
	/**
	 * 存储计划类型.1:前端存储，2中心存储
	 * 
	 * @author gaohe
	 * */
	private String storePlanType, storePlanTypes;

	public String getExsitStorePlanFlag() {
		return exsitStorePlanFlag;
	}

	public void setExsitStorePlanFlag(String exsitStorePlanFlag) {
		this.exsitStorePlanFlag = exsitStorePlanFlag;
	}

	public String getExsitStorePlanFlags() {
		return exsitStorePlanFlags;
	}

	public void setExsitStorePlanFlags(String exsitStorePlanFlags) {
		this.exsitStorePlanFlags = exsitStorePlanFlags;
	}

	public String getStorePlanExeState() {
		return storePlanExeState;
	}

	public void setStorePlanExeState(String storePlanExeState) {
		this.storePlanExeState = storePlanExeState;
	}

	public String getStorePlanExeStates() {
		return storePlanExeStates;
	}

	public void setStorePlanExeStates(String storePlanExeStates) {
		this.storePlanExeStates = storePlanExeStates;
	}

	public String getStoreState() {
		return storeState;
	}

	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

	public String getStoreStates() {
		return storeStates;
	}

	public void setStoreStates(String storeStates) {
		this.storeStates = storeStates;
	}

	public String getStoreState4NoStreamDesc() {
		return storeState4NoStreamDesc;
	}

	public void setStoreState4NoStreamDesc(String storeState4NoStreamDesc) {
		this.storeState4NoStreamDesc = storeState4NoStreamDesc;
	}

	public String getStorePlanType() {
		return storePlanType;
	}

	public void setStorePlanType(String storePlanType) {
		this.storePlanType = storePlanType;
	}

	public String getStorePlanTypes() {
		return storePlanTypes;
	}

	public void setStorePlanTypes(String storePlanTypes) {
		this.storePlanTypes = storePlanTypes;
	}

}

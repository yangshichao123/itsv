package com.data.itsv.model;

/**
 * 用户订阅告警信息
 * @author ghj
 * */
public class UserOrderAlarmRequestModel extends ProtocolRequestModel {

	/**
	 * 申请人编号
	 * @author ghj
	 * */
	private String userId;
	 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}

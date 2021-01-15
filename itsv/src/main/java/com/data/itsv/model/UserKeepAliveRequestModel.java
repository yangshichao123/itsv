package com.data.itsv.model;

/**
 * @author ghj
 * @说明：心跳命令
 * */
public class UserKeepAliveRequestModel extends ProtocolRequestModel {
 

	/**
	 * @author ghj
	 * @说明：编号
	 * */
	private String userId;
	/**
	 * @author ghj
	 * @说明：心跳周期
	 * */
	private String keepAlivePeriod;
	 
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKeepAlivePeriod() {
		return keepAlivePeriod;
	}
	public void setKeepAlivePeriod(String keepAlivePeriod) {
		this.keepAlivePeriod = keepAlivePeriod;
	}
	 
	
}

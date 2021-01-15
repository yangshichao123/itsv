package com.data.itsv.model;

/**
 * @author ghj
 * @说明：用户注册
 * */
public class UserRegisterRequestModel extends ProtocolRequestModel {
	

	/**
	 * @author ghj
	 * @说明：用户编号
	 * */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	  
}

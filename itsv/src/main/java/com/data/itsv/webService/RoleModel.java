package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class RoleModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @author nihuanshan
	 * @说明：角色编号
	 * */
	private String code;
	

	/**
	 * @author nihuanshan
	 * @说明：角色启用状态
	 * */
	private String activeState;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getActiveState() {
		return activeState;
	}


	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	
	
}

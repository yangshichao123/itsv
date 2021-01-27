package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class OrgModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String account;
	private String passwd,desc,status;
	 /**
     * @说明： 上级组织编号，名称
     * */
	private String parentId,parentName;
	 /**
     * @说明：组织编号
     * */
	private String code;
	

	 

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 
	
}

	
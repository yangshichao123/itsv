package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class DirectoryModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author nihuanshan
	 * @说明：目录编号
	 * */
	private String dirCode;
	
	/**
	 * @author nihuanshan
	 * @说明：目录权限
	 * */
	private String resPrivilege;
	private String parentName;
	private String parentId;
	//是否已经存在
	private boolean isFlag = false;
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
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

	public String getDirCode() {
		return dirCode;
	}

	public void setDirCode(String dirCode) {
		this.dirCode = dirCode;
	}

	public String getResPrivilege() {
		return resPrivilege;
	}

	public void setResPrivilege(String resPrivilege) {
		this.resPrivilege = resPrivilege;
	}	
}

package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * 联系人信息
 * @author gaohe
 * */
public class ContactUserModel extends BaseModel {
/**
 * 手机号
 * @author gaohe
 * */
	private String phoneNo;
	/**
	 * 联系人分组信息
	 * */
	private ContactUserGroupModel group;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public ContactUserGroupModel getGroup() {
		return group;
	}
	public void setGroup(ContactUserGroupModel group) {
		this.group = group;
	} 
	
}

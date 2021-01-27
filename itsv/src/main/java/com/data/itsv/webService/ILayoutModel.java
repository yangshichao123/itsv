package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

import java.util.ArrayList;

/**
 * @author wym
 * @说明：我的布局类
 * **/
public class ILayoutModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author wym
	 * @说明：所属用户编号
	 * */
	private String userCode;
	/**
	 * @author wym
	 * @说明：所属用户名称
	 * */
	private String userName;
	/**
	 * @author wym
	 * @说明：分屏数
	 * */
	private String screenNum;
	/**
	 * @author wym
	 * @说明：布局详细信息
	 * */
	private ArrayList<ILayoutDetailModel> detailList;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}

	public ArrayList<ILayoutDetailModel> getDetailList() {
		return detailList;
	}

	public void setDetailList(ArrayList<ILayoutDetailModel> detailList) {
		this.detailList = detailList;
	}

	
}

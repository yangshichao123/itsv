package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

import java.util.ArrayList;

/**
 * @author wym
 * @说明：我的巡视组类
 * **/
public class TourModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author wym
	 * @说明 巡视组编号
	 * */
	private String tourCode;
	/**
	 * @author wym
	 * @说明 巡视组名称
	 * */
	private String tourName;
	
	/**
	 * @author wym
	 * @说明 用户编号
	 * */
	private String userCode;
	/**
	 * @author wym
	 * @说明 巡视组描述
	 * */
	private String tourDescs;
	/**
	 * @author wym
	 * @说明 巡视组时间间隔
	 * */
	private String interval;
	/**
	 * @author wym
	 * @说明 分屏数
	 * */
	private String screenNum;
	/**
	 * @author wym
	 * @说明 巡视组资源信息
	 * */
	private ArrayList<TourDetailModel> detailList;
	public String getTourCode() {
		return tourCode;
	}
	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public String getTourDescs() {
		return tourDescs;
	}
	public void setTourDescs(String tourDescs) {
		this.tourDescs = tourDescs;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getScreenNum() {
		return screenNum;
	}
	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}
	 
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public ArrayList<TourDetailModel> getDetailList() {
		return detailList;
	}
	public void setDetailList(ArrayList<TourDetailModel> detailList) {
		this.detailList = detailList;
	}
}

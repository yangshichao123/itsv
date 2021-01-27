package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class LoginLimitModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author nihuanshan
	 * @说明：用户编号
	 * */
	private String code;
	
	/**
	 * @说明：限制用户登录起始日期
	 * */
	private String startDate;
	
	/**
	 * @说明：限制用户登录起始时间
	 * */
	private String startTime;
	
	/**
	 * @说明：限制用户登录结束日期
	 * */
	private String endDate;
	
	/**
	 * @说明：限制用户登录 结束时间
	 * */
	private String endTime;
	
	/**
	 * @说明：星期一
	 * */
	private String monday;
	
	/**
	 * @说明：星期二
	 * */
	private String tuesday;
	
	/**
	 * @说明：星期三
	 * */
	private String wednesday;
	
	/**
	 * @说明：星期四
	 * */
	private String thursday;
	
	/**
	 * @说明：星期五
	 * */
	private String friday;
	
	/**
	 * @说明：星期六
	 * */
	private String saturday;
	
	/**
	 * @说明：星期日
	 * */
	private String sunday;
	
	/**
	 * @说明：说明
	 * */
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
		
}

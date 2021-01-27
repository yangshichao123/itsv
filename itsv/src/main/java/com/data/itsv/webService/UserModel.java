package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @author nihuanshan
 * @说明：用户模型
 */
public class UserModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author nihuanshan
	 * @说明：部门编号
	 * */
	private String deptCode;
	
	/**
	 * @author nihuanshan
	 * @说明：用户编号
	 * */
	private String userCode;
	
	/**
	 * @author nihuanshan
	 * @说明：职位编号
	 * */
	private String jobCode;
	
	/**
	 * @author nihuanshan
	 * @说明：账号
	 * */
	private String account;
	
	/**
	 * @author nihuanshan
	 * @说明：密码
	 * */
	private String password;
	
	/**
	 * @author nihuanshan
	 * @说明：地址
	 * */
	private String address;
	
	/**
	 * @author nihuanshan
	 * @说明：手机号码
	 * */
	private String phone;
	
	/**
	 * @author nihuanshan
	 * @说明：座机号码
	 * */
	private String telPhone;
	
	/**
	 * @author nihuanshan
	 * @说明：在线状态
	 * */
	private String loginState;
	
	/**
	 * @author nihuanshan
	 * @说明：上线时间
	 * */
	private String loginTime;
	
	/**
	 * @author nihuanshan
	 * @说明：下线时间
	 * */
	private String logoutTime;
	
	/**
	 * @author nihuanshan
	 * @说明：用户等级：1、2、3、4、5共5个等级，级别依次升高
	 * */
	private String level;
	
	/**
	 * @author nihuanshan
	 * @说明：用户启用状态：0未启用，1启用
	 * */
	private String activeState;
	
	/**
	 * @author nihuanshan
	 * @说明：邮箱地址
	 * */
	private String eMail;
	
	/**
	 * @author nihuanshan
	 * @说明：单位名称
	 * */
	private String orgName;

	/**
	 * @author nihuanshan
	 * @说明：职位名称
	 * */
	private String jobName;
	
	/**
	 * @author nihuanshan
	 * @说明：用户启用状态：1-启用，0-未启用
	 * */
	private String timeLimitState;
	
	/**
	 * @author nihuanshan
	 * @说明：ip
	 * */
	private String ip;
	/**
	 * 登陆次数统计
	 * @author gaohejie
	 * */
	private int loginTimeCount;
	 
	public int getLoginTimeCount() {
		return loginTimeCount;
	}

	public void setLoginTimeCount(int loginTimeCount) {
		this.loginTimeCount = loginTimeCount;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTimeLimitState() {
		return timeLimitState;
	}

	public void setTimeLimitState(String timeLimitState) {
		this.timeLimitState = timeLimitState;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}		
	
}

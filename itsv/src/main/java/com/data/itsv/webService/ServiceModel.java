package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @author zml
 * **/
public class ServiceModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @说明： 服务编号
	 * */
	private String code;
	/**
	 * @说明：-- '上级服务。服务类型为PAG时该字段含义为VTDU；服务类型为VTDU时该字段含义为CMS，服务类型为存储服务该字段含义为cms';
	 * */
	private String parentCode;

	/**
	 * @说明： 监听端口
	 * */
	private String acceptPort;
	/**
	 * @说明：服务类型:1视频中心管理服务，2设备接入服务，5网关服务，3数据转发服务，4智能交通接入服务;5存储服务器，6智能交通服务;7告警单元';
	 * */
	private String type, types;
	/**
	 * @说明： 物理服务器
	 * */
	private String machine;
	/**
	 * @说明： 机构编码(唯一)
	 * */
	private String orgId;
	/**
	 * @说明： 服务器用户名 ;登陆用户名 上报上来的sa或mu服务器';
	 * */
	private String account;
	/**
	 * @说明：服务器密码;登陆密码 md5 上报上来的sa或mu服务器';
	 * */
	private String password;
	/**
	 * @说明：'厂家标识;上报上来的服务器使用';
	 * */
	private String facturerId, facturerName;
	/**
	 * @说明：IP地址
	 * */
	private String ip;
	/**
	 * @说明： 协议版本;上报上来的服务器使用
	 * */
	private String protocolVersion;
	/**
	 * @说明： 软件版本 ;上报上来的服务器使用
	 * */
	private String softWareVersion;
	/**
	 * @说明： 心跳周期;上报上来的服务器使用
	 * */
	private String heartCycle;
	/**
	 * @说明： 是否本地服务器;0:非本地服务器(上报上来的sa或mu服务器) 1:本地服务器';
	 * */
	private String localFlag;
	/**
	 * @说明：安装地点
	 * */
	private String address;
	/**
	 * @说明： 安装人员
	 * */
	private String installUser;
	/**
	 * @说明：扩展字段
	 * */
	private String extField;

	/**
	 * @说明： 安装日期
	 * */
	private String installTime;
	/**
	 * @说明： 经度
	 * */
	private String longitude;
	/**
	 * @说明：纬度
	 * */
	private String latitude;

	/**
	 * @说明：修改时间'
	 * */
	private String updateTime;
	/**
	 * @说明： '服务器状态'
	 * */
	private String state, states;

	/**
	 * @说明：pag编号
	 * */
	private String auCode;
	/**
	 * @说明：pss编号
	 * */
	private String pssCode;

	private String auName;
	/**
	 *@说明：语音监听端口
	 **/
	private String phonicAccPort;
	/**
	 *服务在线下线状态
	 **/
	private String onlineState, onlineStates;
	/**
	 *上线时间
	 **/
	private String onlineTime;
	/**
	 *下线时间
	 **/
	private String offlineTime;

	public String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	public String getOnlineStates() {
		return onlineStates;
	}

	public void setOnlineStates(String onlineStates) {
		this.onlineStates = onlineStates;
	}

	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAuName() {
		return auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}

	public String getPhonicAccPort() {
		return phonicAccPort;
	}

	public void setPhonicAccPort(String phonicAccPort) {
		this.phonicAccPort = phonicAccPort;
	}

	public String getInstallUser() {
		return installUser;
	}

	public void setInstallUser(String installUser) {
		this.installUser = installUser;
	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getAcceptPort() {
		return acceptPort;
	}

	public void setAcceptPort(String acceptPort) {
		this.acceptPort = acceptPort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getFacturerId() {
		return facturerId;
	}

	public void setFacturerId(String facturerId) {
		this.facturerId = facturerId;
	}

	public String getFacturerName() {
		return facturerName;
	}

	public void setFacturerName(String facturerName) {
		this.facturerName = facturerName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getSoftWareVersion() {
		return softWareVersion;
	}

	public void setSoftWareVersion(String softWareVersion) {
		this.softWareVersion = softWareVersion;
	}

	public String getHeartCycle() {
		return heartCycle;
	}

	public void setHeartCycle(String heartCycle) {
		this.heartCycle = heartCycle;
	}

	public String getLocalFlag() {
		return localFlag;
	}

	public void setLocalFlag(String localFlag) {
		this.localFlag = localFlag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getExtField() {
		return extField;
	}

	public void setExtField(String extField) {
		this.extField = extField;
	}

	public String getAuCode() {
		return auCode;
	}

	public void setAuCode(String auCode) {
		this.auCode = auCode;
	}

	public String getPssCode() {
		return pssCode;
	}

	public void setPssCode(String pssCode) {
		this.pssCode = pssCode;
	}
}

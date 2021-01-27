package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

import java.util.ArrayList;

/**
 * @author ghj
 * @说明：告警资源类
 * */
public class AlarmResModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author ghj
	 * @说明：告警资源ID（20位编码，分为：镜头ID、行为分析设备通道或IO通道）
	 * */
	private String  resId	;
	
	/**
	 * @author nihuanshan
	 * @说明：告警资源编号
	 * */
	private String  alarmSetId;
	
	/**
	 * @author ghj
	 * @说明：告警类型一个告警资源的告警类型不能重复，如果告警子类型为全部时，不能再有其它子类型。
	 * */
	private String  alarmType,alarmTypes	;	
	/**
	 * @author ghj
	 * @说明：告警源名称
	 * */
	private String  name	;	
	/**
	 * @author ghj
	 * @说明：通道号
	 * */
	private String  channelNum	;
	/**
	 * @author ghj
	 * @说明：告警级别
	 * */
	private String  level	;
	
	/**
	 * @author nihuanshan
	 * @说明：告警级别
	 * */
	private String  levels	;
	
	/**
	 * @author ghj
	 * @说明：是否联动地图
	 * */
	private String  isLinkMap	;
	/**
	 * @author ghj
	 * @说明：是否布防
	 * */
	private String  isArming	;	
	/**
	 * @author ghj
	 * @说明：是否联动声音
	 * */
	private String  isLinkSoud	;
	/**
	 * @author ghj
	 * @说明：告警资源所属设备ip
	 * */
	private String alarmFDIp	;	
	 

	/**
	 * @author ghj
	 * @说明：告警资源所属设备port
	 * */
	private String alarmFDPort	;
	/**
	 * @author ghj
	 * @说明：登录设备的用户名
	 * */
	private String alarmFDUserName;
	/**
	 * @author ghj
	 * @说明：登录设备的密码
	 * */
	private String alarmFDPassWord	;
	/**
	 * @author ghj
	 * @说明：布防列表
	 * */
	private ArrayList<ArmingModel> armingList;
	/**
	 * @author ghj
	 * @说明：告警联动设置
	 * */
	private ArrayList<LinkModel> linkList;
	
	/**
	 * @author madi
	 * @说明：经度
	 * 
	 * */
	private String longitude;
	/**
	 * @author madi
	 * @说明：纬度
	 * 
	 * */
	private String latitude;
	/**
	 * @author ghj
	 * @说明：告警资源所属设备编号ID（20位编码）
	 * */
	private String deviceId;
	/**
	 * @author madi
	 * @说明：pag编号
	 * 
	 * */
	private String pagCode;
	/**
	 * @author madi
	 * @说明：au编号
	 * 
	 * */
	private String auCode;
	
	 
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmTypes() {
		return alarmTypes;
	}
	public void setAlarmTypes(String alarmTypes) {
		this.alarmTypes = alarmTypes;
	}
	public String getPagCode() {
		return pagCode;
	}
	public void setPagCode(String pagCode) {
		this.pagCode = pagCode;
	}
	public String getAuCode() {
		return auCode;
	}
	public void setAuCode(String auCode) {
		this.auCode = auCode;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getLongitude() {
		return longitude;
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
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getAlarmSetId() {
		return alarmSetId;
	}
	public void setAlarmSetId(String alarmSetId) {
		this.alarmSetId = alarmSetId;
	}
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChannelNum() {
		return channelNum;
	}
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}
	 
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getIsLinkMap() {
		return isLinkMap;
	}
	public void setIsLinkMap(String isLinkMap) {
		this.isLinkMap = isLinkMap;
	}
	public String getIsArming() {
		return isArming;
	}
	public void setIsArming(String isArming) {
		this.isArming = isArming;
	}
	public String getIsLinkSoud() {
		return isLinkSoud;
	}
	public void setIsLinkSoud(String isLinkSoud) {
		this.isLinkSoud = isLinkSoud;
	}
	public String getAlarmFDIp() {
		return alarmFDIp;
	}
	public void setAlarmFDIp(String alarmFDIp) {
		this.alarmFDIp = alarmFDIp;
	}
	public String getAlarmFDPort() {
		return alarmFDPort;
	}
	public void setAlarmFDPort(String alarmFDPort) {
		this.alarmFDPort = alarmFDPort;
	}
	public String getAlarmFDUserName() {
		return alarmFDUserName;
	}
	public void setAlarmFDUserName(String alarmFDUserName) {
		this.alarmFDUserName = alarmFDUserName;
	}
	public String getAlarmFDPassWord() {
		return alarmFDPassWord;
	}
	public void setAlarmFDPassWord(String alarmFDPassWord) {
		this.alarmFDPassWord = alarmFDPassWord;
	}
	public ArrayList<ArmingModel> getArmingList() {
		return armingList;
	}
	public void setArmingList(ArrayList<ArmingModel> armingList) {
		this.armingList = armingList;
	}
	public ArrayList<LinkModel> getLinkList() {
		return linkList;
	}
	public void setLinkList(ArrayList<LinkModel> linkList) {
		this.linkList = linkList;
	}
	
}

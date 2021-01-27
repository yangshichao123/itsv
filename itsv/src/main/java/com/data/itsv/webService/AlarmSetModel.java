package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class AlarmSetModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author madi
	 * @说明：告警资源ID
	 */
	private String alarmResId;
	/**
	 * @author madi
	 * @说明：告警类型
	 */
	private String alarmType,alarmTypes;
	/**
	 * @author madi
	 * @说明：告警级别
	 */
	private String alarmLevel;
	
	/**
	 * @author nihuanshan
	 * @说明：告警级别
	 */
	private String Levels;
	
	/**
	 * @author madi
	 * @说明：是否联动地图0不联动，1联动
	 */
	private String isLinkMap;
	/**
	 * @author madi
	 * @说明：是否布防，0不不妨，1不妨
	 */
	private String isArming;
	/**
	 * @author madi
	 * @说明：是否联动声音，0不联动，1联动';
	 */
	private String isLinkSound;
	/**
	 * @author madi
	 * @说明：联动声音URL
	 */
	private String sound;
	/**
	 * @author madi
	 * @说明：编号
	 */
	private String alarmSetId;
	
	public String getAlarmTypes() {
		return alarmTypes;
	}
	public void setAlarmTypes(String alarmTypes) {
		this.alarmTypes = alarmTypes;
	}
	public String getAlarmResId() {
		return alarmResId;
	}
	public void setAlarmResId(String alarmResId) {
		this.alarmResId = alarmResId;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
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
	public String getIsLinkSound() {
		return isLinkSound;
	}
	public void setIsLinkSound(String isLinkSound) {
		this.isLinkSound = isLinkSound;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getAlarmSetId() {
		return alarmSetId;
	}
	public void setAlarmSetId(String alarmSetId) {
		this.alarmSetId = alarmSetId;
	}
	public String getLevels() {
		return Levels;
	}
	public void setLevels(String levels) {
		this.Levels = levels;
	}		

}

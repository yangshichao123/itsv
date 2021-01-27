package com.data.itsv.webService;

/**
 * @author madi
 * @说明 告警查询信息
 * */
public class AlarmInfoModel extends AMQAlarmInforModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author madi
	 * @说明 报警ID
	 * */
	private String alarmId;
	/**
	 * @author madi
	 * @说明 存储的录像文件
	 * */
	private String videoURL;
	 
	
	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	
	
	
}

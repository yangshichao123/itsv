package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;
import lombok.Data;

/**
 * @author ghj
 * @说明：AMQ上报上来的摄像机状态信息。
 * */
@Data
public class AMQAlarmInforModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author ghj
	 * @说明：告警资源ID
	 * */
	private Integer id;
	/**
	 * @author ghj
	 * @说明：告警唯一标识
	 * */
	private String sessionId;

	/**
	 * @author ghj
	 * @说明：告警源名称
	 * */
	private String name;
	/**
	 * @author ghj
	 * @说明：描述
	 * */
	private String description;
	/**
	 * @author ghj
	 * @说明：告警类型
	 * */
	private String alarmType,alarmTypes;
	/**
	 * @author ghj
	 * @说明：告警级别
	 * */
	private String alarmLevel;
	/**
	 * @author ghj
	 * @说明：告警开始时间
	 * */
	private String beginTime;
	/**
	 * @author ghj
	 * @说明：告警结束时间
	 * */
	private String endTime;
	/**
	 * @author ghj
	 * @说明：1：告警0：正常
	 * */
	private String alarmState;
	/**
	 * @author ghj
	 * @说明：确认人，用户的20位编码
	 * */
	private String affirmMan;

	/**
	 * @author ghj
	 * @说明：确认时间
	 * */
	private String affirmTime;
	/**
	 * @author ghj
	 * @说明：清除人，用户的20位编码
	 * */
	private String cleanMan;

	/**
	 * @author ghj
	 * @说明：清除时间
	 * */
	private String cleanTime;
	/**
	 * @author ghj
	 * @说明：告警信息编号
	 * */
	private String alarmId;



}

package com.data.itsv.webService;


import com.data.itsv.model.BaseModel;

/**
 * @author ghj
 * @说明：告警布防类
 * */
public class ArmingModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author ghj
	 * @说明：布防开始时间
	 * */
  private String 	startTime	;
  /**
   * @author ghj
   * @说明：布防结束时间
   * */
  private String 	endTime;
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}

}

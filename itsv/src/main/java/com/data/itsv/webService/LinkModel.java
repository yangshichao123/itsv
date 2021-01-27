package com.data.itsv.webService;


import com.data.itsv.model.BaseModel;

/**
 * @author ghj
 * @说明：告警联动类
 * */
public class LinkModel  extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author ghj
	 * @说明：	联动镜头ID
	 * */
	private String resId	;
	/**
	 * @author madi
	 * @说明： 联动镜头状态
	 */
	private String resState;
	
	/**
	 * @author madi
	 * @说明： 联动镜头对应的语言通道ID
	 */
	private String resVoice;
	/**
	 * @author madi
	 * @说明： 联动镜头联动镜头主子码流标识 
	 */
	private String realCodeType;
	
	/**
	 * @author madi
	 * @说明： 联动镜头是否云台标志
	 */
	private String ptzFlag;
	
	
	
	
	
	
	public String getRealCodeType() {
		return realCodeType;
	}
	public void setRealCodeType(String realCodeType) {
		this.realCodeType = realCodeType;
	}
	public String getResState() {
		return resState;
	}
	public void setResState(String resState) {
		this.resState = resState;
	}
	public String getResVoice() {
		return resVoice;
	}
	public void setResVoice(String resVoice) {
		this.resVoice = resVoice;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getIsLinkPtz() {
		return isLinkPtz;
	}
	public void setIsLinkPtz(String isLinkPtz) {
		this.isLinkPtz = isLinkPtz;
	}
	public String getPtzPresent() {
		return ptzPresent;
	}
	public void setPtzPresent(String ptzPresent) {
		this.ptzPresent = ptzPresent;
	}
	public String getIsLinkRec() {
		return isLinkRec;
	}
	public void setIsLinkRec(String isLinkRec) {
		this.isLinkRec = isLinkRec;
	}
	public String getRecFps() {
		return recFps;
	}
	public void setRecFps(String recFps) {
		this.recFps = recFps;
	}
	public String getIsLinkPrev() {
		return isLinkPrev;
	}
	public void setIsLinkPrev(String isLinkPrev) {
		this.isLinkPrev = isLinkPrev;
	}
	public String getIsLinkPic() {
		return isLinkPic;
	}
	public void setIsLinkPic(String isLinkPic) {
		this.isLinkPic = isLinkPic;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPtzFlag(String ptzFlag) {
		this.ptzFlag = ptzFlag;
	}
	public String getPtzFlag() {
		return ptzFlag;
	}
	/**
	 * @author ghj
	 * @说明：	是否联动ptz
	 * */
	private String isLinkPtz	;
	/**
	 * @author ghj
	 * @说明：	预置位
	 * */
	private String ptzPresent;
	/**
	 * @author ghj
	 * @说明：	是否联动录像
	 * */
	private String isLinkRec	;
	/**
	 * @author ghj
	 * @说明：	录像的帧率
	 * */
	private String recFps	;
	/**
	 * @author ghj
	 * @说明：	是否联动实时视频
	 * */
	private String isLinkPrev;	
	/**
	 * @author ghj
	 * @说明：	是否联动抓拍
	 * */
	private String isLinkPic;

}

package com.data.itsv.webService;


import com.data.itsv.model.BaseModel;

/**
 * @author ghj
 * @说明 我的巡视组详细设置类
 * **/
public class TourDetailModel extends BaseModel {
	/**
	 * @author ghj
	 * @说明 摄像机信息
	 * **/
	private VideoModel video;
	
	/**
	 * @author ghj
	 * @说明 顺序号
	 * **/
	private int orderNum;

	public VideoModel getVideo() {
		return video;
	}

	public void setVideo(VideoModel video) {
		this.video = video;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}

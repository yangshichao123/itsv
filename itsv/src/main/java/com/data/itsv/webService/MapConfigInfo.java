package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

public class MapConfigInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 说明：底图
	 * */
	private String baseMapUrl;
	/**
	 * 说明：摄像机地图
	 * */
	private String camerMapUrl;
	/**
	 * 说明：区域地图
	 * */
	private String areaMapUrl;
	/**
	 * 说明：GeometryService
	 * */
	private String geometryServiceUrl;
	/**
	 * 说明：参考系
	 * */
	private String wkid;
	/**
	 * 说明：最大比例尺
	 * */
	private String maxScale;
	public String getAreaMapUrl() {
		return areaMapUrl;
	}
	public void setAreaMapUrl(String areaMapUrl) {
		this.areaMapUrl = areaMapUrl;
	}
	/**
	 * 说明：最小比例尺
	 * */
	private String minScale;
	/**
	 * 说明：地图范围xmin
	 * */
	private String mapXmin;
	/**
	 * 说明：地图范围ymin
	 * */
	private String mapYmin;
	/**
	 * 说明：地图范围ymax
	 * */
	private String mapYmax;
	/**
	 * 说明：地图范围xmax
	 * */
	private String mapXmax;
	public String getMapXmin() {
		return mapXmin;
	}
	public void setMapXmin(String mapXmin) {
		this.mapXmin = mapXmin;
	}
	
	public String getMapYmin() {
		return mapYmin;
	}
	public void setMapYmin(String mapYmin) {
		this.mapYmin = mapYmin;
	}
	
	public String getMapYmax() {
		return mapYmax;
	}
	public void setMapYmax(String mapYmax) {
		this.mapYmax = mapYmax;
	}
	
	public String getMapXmax() {
		return mapXmax;
	}
	public void setMapXmax(String mapXmax) {
		this.mapXmax = mapXmax;
	}
	
	public String getBaseMapUrl() {
		return baseMapUrl;
	}
	public void setBaseMapUrl(String baseMapUrl) {
		this.baseMapUrl = baseMapUrl;
	}
	
	public String getCamerMapUrl() {
		return camerMapUrl;
	}
	public void setCamerMapUrl(String camerMapUrl) {
		this.camerMapUrl = camerMapUrl;
	}
	
	 
	public String getGeometryServiceUrl() {
		return geometryServiceUrl;
	}
	public void setGeometryServiceUrl(String geometryServiceUrl) {
		this.geometryServiceUrl = geometryServiceUrl;
	}
	
	public String getWkid() {
		return wkid;
	}
	public void setWkid(String wkid) {
		this.wkid = wkid;
	}
	
	public String getMaxScale() {
		return maxScale;
	}
	public void setMaxScale(String maxScale) {
		this.maxScale = maxScale;
	}
	
	public String getMinScale() {
		return minScale;
	}
	public void setMinScale(String minScale) {
		this.minScale = minScale;
	}
	
}

package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @author ghj
 * @说明 版本信息
 * */
public class VersionModel extends BaseModel {
	/**
	 * @author ghj
	 * @说明 版本
	 * */
	private String version;
	/**
	 * @author ghj
	 * @说明 下载地址
	 * */
	private String url;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

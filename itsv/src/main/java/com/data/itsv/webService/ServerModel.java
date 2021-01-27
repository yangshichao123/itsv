package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;

/**
 * @说明 服务信息
 * */
public class ServerModel extends BaseModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @说明 IP地址
	 * */
	private String serverIp;
	/**
	 * @说明 端口
	 * */
	private String serverPort;
	/**
	 * @说明 帐号
	 * */
	private String account;
	/**
	 * @说明 密码
	 * */
	private String password;
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
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
	
}

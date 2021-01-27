package com.data.itsv.webService;

import com.data.itsv.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @author ghj
 * @说明 登录用户信息
 * */
@Data
public class LoginUserModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author ghj
	 * @说明 用户基本信息
	 * */
	private UserModel userInfo;
	
	/**
	 * @author ghj
	 * @说明 登录时间
	 * */
	private Date loginTime;
	/**
	 * @author ghj
	 * @说明：最后心跳时间
	 * */
	private Date lastHeartBeatTime;
	/**
	 * @author ghj
	 * @说明 用户模块权限
	 * */
	private String funcPrivilege;
	
	/**
	 * @author ghj
	 * @说明 用户登录状态
	 * */
	private String loginState;
	/**
	 * @author ghj
	 * @说明 cmsIp
	 * */
	private String cmsIP;
	/**
	 * @author ghj
	 * @说明 cmsPort
	 * */
	private String cmsPort;
	
	/**
	 * @author ghj
	 * @说明 ftp
	 * */
	private ServerModel ftpServer;
	
	/**
	 * @author ghj
	 * @说明 视频服务器
	 * */
	private ServerModel mompServer;
	
	/**
	 * @author ghj
	 * @说明 socket服务器
	 * */
	private ServerModel bscServer;
 

}

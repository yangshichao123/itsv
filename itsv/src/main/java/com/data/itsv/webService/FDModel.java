package com.data.itsv.webService;

/**
 * 设备信息类
 */
public class FDModel extends FDTemplateModel {

	private static final long serialVersionUID = 1L;
	/**
	 * 设备型号编号
	 */
	private String fdTemplateId;
	/**
	 * 设备型号名称
	 */
	private String fdTemplateName;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 设备编号
	 */
	private String code;
	/**
	 * 扩展编号
	 */
	private String extCode;
	/**
	 * 目录编号
	 */
	private String dirId;
	/**
	 * 设备账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * IP
	 */
	private String IP;
	/**
	 * 端口
	 */
	private String port;
	/**
	 * 创建人编号
	 */
	private String createUserCode;
	/**
	 *修改人编号
	 */
	private String updateUserCode;
	/**
	 *所在位置
	 */
	private String address;
	/**
	 *所属接入服务器
	 */
	private String serverCode;
	/**
	 * 设备类型
	 * */
	private FDTemplateModel fdTemplate;
	/**
	 * 设备协议类型主设备协议类型：（长度限制64字节）;TEYES：千里眼,ONVIF：开放型网络视频接口协议,HWSDK：华为SDK,HIKSDK：海康SDK,T28181,DHSDK：大华SDK,HBSDK,HESDK,HIKSDK2：海康SDK2,DHSDK2：大华SDK2

	 * */
	private String protocolType;

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public FDTemplateModel getFdTemplate() {
		return fdTemplate;
	}

	public void setFdTemplate(FDTemplateModel fdTemplate) {
		this.fdTemplate = fdTemplate;
	}

	public String getFdTemplateId() {
		return fdTemplateId;
	}

	public void setFdTemplateId(String fdTemplateId) {
		this.fdTemplateId = fdTemplateId;
	}

	public String getFdTemplateName() {
		return fdTemplateName;
	}

	public void setFdTemplateName(String fdTemplateName) {
		this.fdTemplateName = fdTemplateName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExtCode() {
		return extCode;
	}

	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	public void setDirId(String dirId) {
		this.dirId = dirId;
	}

	public String getDirId() {
		return dirId;
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

	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		this.IP = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

}

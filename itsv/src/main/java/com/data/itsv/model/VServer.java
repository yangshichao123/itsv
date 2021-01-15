package com.data.itsv.model;

import java.io.Serializable;
import java.util.Date;

public class VServer implements Serializable {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 服务编号
     */
    private String code;

    /**
     * 上级服务。服务类型为PAG时该字段含义为VTDU；服务类型为VTDU时该字段含义为CMS，服务类型为存储服务该字段含义为cms
     */
    private String parentCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;

    /**
     * 视频监听端口
     */
    private Integer accPort;

    /**
     * 服务类型:1视频中心管理服务，2设备接入服务，3数据转发服务,5存储服务器,7告警单元,8语音存储服务,9图片存储服务，10事件存储服务,11视频分析服务
     */
    private Integer type;

    /**
     * 物理服务器
     */
    private String machine;

    /**
     * 机构编码(唯一)
     */
    private Integer orgid;

    /**
     * 服务器用户名  ,登陆用户名 上报上来的sa或mu服务器
     */
    private String account;

    /**
     * 服务器密码,登陆密码 md5 上报上来的sa或mu服务器
     */
    private String password;

    /**
     * 厂家标识,上报上来的服务器使用
     */
    private String facturerid;

    /**
     * 协议版本,上报上来的服务器使用
     */
    private String protocolVersion;

    /**
     * 软件版本 ,上报上来的服务器使用
     */
    private String softwareVersion;

    /**
     * 心跳周期,上报上来的服务器使用
     */
    private Integer heartCycle;

    /**
     * 是否本地服务器,0:非本地服务器(上报上来的sa或mu服务器) 1:本地服务器
     */
    private Integer localFlag;

    /**
     * 安装地点
     */
    private String address;

    /**
     * 安装人员
     */
    private String installUser;

    /**
     * 安装日期 
     */
    private Date installTime;

    /**
     * 经度
     */
    private Integer longitude;

    /**
     * 纬度 
     */
    private Integer latitude;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 服务器状态1启用0停用
     */
    private Integer state;

    /**
     * 
     */
    private String ip;

    /**
     * au编号，该字段在服务为接入网关服务表示和AU关联关系
     */
    private String auCode;

    /**
     * 语音监听端口，该字段为Pag、VTDU、Ass服务时该字段有效
     */
    private Integer phonicAccPort;

    /**
     * pss编号，该字段在服务为接入网关服务表示和AU关联关系
     */
    private String pssCode;

    /**
     * 服务上线时间
     */
    private Date onlineTime;

    /**
     * 服务下线时间
     */
    private Date offlineTime;

    /**
     * 服务上下线状态,1上线，0下线
     */
    private Integer onlineState;

    /**
     * 会话编号
     */
    private String sessionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_server
     *
     * @mbg.generated Mon Dec 28 11:00:29 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs == null ? null : descs.trim();
    }

    public Integer getAccPort() {
        return accPort;
    }

    public void setAccPort(Integer accPort) {
        this.accPort = accPort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine == null ? null : machine.trim();
    }

    public Integer getOrgid() {
        return orgid;
    }

    public void setOrgid(Integer orgid) {
        this.orgid = orgid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFacturerid() {
        return facturerid;
    }

    public void setFacturerid(String facturerid) {
        this.facturerid = facturerid == null ? null : facturerid.trim();
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion == null ? null : protocolVersion.trim();
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion == null ? null : softwareVersion.trim();
    }

    public Integer getHeartCycle() {
        return heartCycle;
    }

    public void setHeartCycle(Integer heartCycle) {
        this.heartCycle = heartCycle;
    }

    public Integer getLocalFlag() {
        return localFlag;
    }

    public void setLocalFlag(Integer localFlag) {
        this.localFlag = localFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getInstallUser() {
        return installUser;
    }

    public void setInstallUser(String installUser) {
        this.installUser = installUser == null ? null : installUser.trim();
    }

    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getAuCode() {
        return auCode;
    }

    public void setAuCode(String auCode) {
        this.auCode = auCode == null ? null : auCode.trim();
    }

    public Integer getPhonicAccPort() {
        return phonicAccPort;
    }

    public void setPhonicAccPort(Integer phonicAccPort) {
        this.phonicAccPort = phonicAccPort;
    }

    public String getPssCode() {
        return pssCode;
    }

    public void setPssCode(String pssCode) {
        this.pssCode = pssCode == null ? null : pssCode.trim();
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", name=").append(name);
        sb.append(", descs=").append(descs);
        sb.append(", accPort=").append(accPort);
        sb.append(", type=").append(type);
        sb.append(", machine=").append(machine);
        sb.append(", orgid=").append(orgid);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", facturerid=").append(facturerid);
        sb.append(", protocolVersion=").append(protocolVersion);
        sb.append(", softwareVersion=").append(softwareVersion);
        sb.append(", heartCycle=").append(heartCycle);
        sb.append(", localFlag=").append(localFlag);
        sb.append(", address=").append(address);
        sb.append(", installUser=").append(installUser);
        sb.append(", installTime=").append(installTime);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", state=").append(state);
        sb.append(", ip=").append(ip);
        sb.append(", auCode=").append(auCode);
        sb.append(", phonicAccPort=").append(phonicAccPort);
        sb.append(", pssCode=").append(pssCode);
        sb.append(", onlineTime=").append(onlineTime);
        sb.append(", offlineTime=").append(offlineTime);
        sb.append(", onlineState=").append(onlineState);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
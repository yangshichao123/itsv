package com.data.itsv.netty;

import com.data.itsv.model.CameraOSDInfoModel;
import com.data.itsv.model.OSDModel;
import com.data.itsv.model.RequestModel;
import com.data.itsv.netty.client.BootNettyChannelInboundHandlerAdapter;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.ProtocolService;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author ghj
 * @说明：协议处理器
 * */
@Slf4j
@Component
public class ProtocolResponseProcesser {
	// 加锁器
	private  ReentrantLock lock = new ReentrantLock();

	private  SAXBuilder saxBuilder = new SAXBuilder();

	// 协议服务类
	@Autowired
	private  ProtocolService ebi;
	@Autowired
	private ProtocolRequest pro;
	@Value("${setVideoCode}")
	private String serverCode;
	@Value("${userName}")
	private String userName;
	@Value("${password}")
	private String password;


	public  void process(byte byteXml[], RequestModel rm) {
		// 构造xml
		Document doc = getDoc(byteXml);
		if (doc != null) {
			// 解析xml
			Element rootEle = doc.getRootElement();

			// 解析命令
			String cmd = rootEle.getAttributeValue("command");

			if (cmd != null) {
				// 解析参数
				Element paraEle = rootEle.getChild("parameters");
				if (paraEle != null) {
					// 向cms注册返回值0
					if (cmd.trim().equalsIgnoreCase("Register")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS注册成功!");
							String keepAlivePeriod = paraEle
									.getChildTextTrim("keepAlivePeriod");
							if (keepAlivePeriod != null
									&& keepAlivePeriod.trim().length() > 0) {
								BootNettyChannelInboundHandlerAdapter.keepAlivePeriod = Long.parseLong(keepAlivePeriod);
							}
							// 注册成功发送心跳
							BootNettyChannelInboundHandlerAdapter.heartBeatJob.heartBeatFlag = true;

							// 订阅信息
							this.orderMessage();
							// 发送未发送成功数据
							//BootNettyChannelInboundHandlerAdapter.sendUnSucceedData();
						} else {
							log.info("ITSV向CMS注册失败!");
							try {
								// 注册成功发送心跳
								BootNettyChannelInboundHandlerAdapter.heartBeatJob.heartBeatFlag = false;
								Thread.sleep(5000);
								this.register2CMS();

							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}
					// 向cms保持心跳返回值
					else if (cmd.trim().equalsIgnoreCase("KeepAlive")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送心跳成功!");
						}
					}
					// 接受cms资源变更返回值
					else if (cmd.trim().equalsIgnoreCase("ResourceChange")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送资源变更成功!");
						}

					}// 存储计划更新返回值
					else if (cmd.trim().equalsIgnoreCase("ChangeStoragePlan")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送存储计划变更成功!");
						}

					}
					// 镜头资源更新返回值
					else if (cmd.trim().equalsIgnoreCase("ChangeCamResource")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送摄像机资源变更成功!");
						}

					}
					// 设备资源更新返回值
					else if (cmd.trim()
							.equalsIgnoreCase("ChangeDeviceResource")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送设备资源变更成功!");
						}

					}
					// 告警资源资源更新返回值
					else if (cmd.trim().equalsIgnoreCase("ChangeAlarmResource")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送告警资源变更成功!");
						}

					}
					// 服务资源更新返回值
					else if (cmd.trim().equalsIgnoreCase(
							"ChangeServiceResource")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送服务资源变更成功!");
						}

					}
					// 语音资源更新返回值
					else if (cmd.trim().equalsIgnoreCase("ChangeVoiceResource")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送语音资源变更成功!");
						}

					}
					// 视频流上大屏
					else if (cmd.trim().equalsIgnoreCase(
							"SendVideo2ScreenSystem")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("ITSV向CMS发送视频流上大屏!");
						}
					}
					// 设备启动
					else if (cmd.trim().equalsIgnoreCase("RebootFd")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送设备启动成功");
						}
					}
					// 获取设备从属关系(NVR和IPC关系)
					else if (cmd.trim().equalsIgnoreCase("GetNVRChannelInfo")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						Element groupElement = paraEle
								.getChild("channelInfoGroup");
						List children = groupElement.getChildren();
						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String deviceCode = e
									.getChildTextTrim("deviceCode");
							String resId = e.getChildTextTrim("resId");
							String ip = e.getChildTextTrim("ip");
							String port = e.getChildTextTrim("port");
							String userName = e.getChildTextTrim("userName");
							String password = e.getChildTextTrim("password");

							ebi.reportNVRChannelInfo(deviceCode, resId,
									userName, password, ip, port);
						}

						{

							rootEle = null;
							children = null;
							groupElement = null;
							paraEle = null;
							rootEle = null;
							doc = null;
						}
					}
					// 动态增加NVR设备通道（IPC）
					else if (cmd.trim().equalsIgnoreCase("SetNVRChannelInfo")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送动态增加NVR设备通道（IPC）成功");
						}
					}

					// 查询NVR、DVR设备下摄像机OSD信息
					else if (cmd.trim().equalsIgnoreCase("GetNVRChannelInfo")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送查询NVR、DVR设备下摄像机OSD信息成功");
						}
					}
					// 设置摄像机OSD信息
					else if (cmd.trim().equalsIgnoreCase("SetCameraOSD")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送设置摄像机OSD信息成功");
						}
					}
					// 开始实时录像
					else if (cmd.trim().equalsIgnoreCase(
							"StartRecord4RealTimeVideo")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送开始实时录像成功");
						}
					}
					// 停止实时录像
					else if (cmd.trim().equalsIgnoreCase(
							"StopRecord4RealTimeVideo")) {
						String result = rootEle.getChild("result")
								.getAttributeValue("code");
						if ("0".equals(result)) {
							log.info("发送停止实时录像成功");
						}
					}
					// 查询设备OSD信息

					else if (cmd.trim().equalsIgnoreCase("GetFDOSD")) {
						ArrayList<CameraOSDInfoModel> cameraOSDList = new ArrayList<CameraOSDInfoModel>();
						Element groupElement = paraEle.getChild("cameraOSDGroup");
						List children = groupElement.getChildren();


						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);
							Element timeOSDE = e.getChild("timeOSD");
							Element channelNameOSDE = e
									.getChild("channelNameOSD");
							Element appendOSDGroupE = e
									.getChild("appendOSDGroup");
							String resCode = e.getChildText("resCode");
							String deviceCode = e.getChildText("deviceCode");
							String channelNum = e.getChildText("channelNum");

							CameraOSDInfoModel temp = new CameraOSDInfoModel();
							temp.setVideoCode(resCode);
							temp.setFdCode(deviceCode);
							temp.setChannelNum(channelNum);
							/**
							 * 时间OSD信息
							 * 
							 * @author gaohe
							 * */
							OSDModel timeOSD = new OSDModel();
							temp.setTimeOSD(timeOSD);
							timeOSD
									.setContent(timeOSDE
											.getChildText("content"));
							try {
								timeOSD.setEnableHide(Integer.parseInt(timeOSDE
										.getChildText("enableHide")));
								timeOSD.setX(Integer.parseInt(timeOSDE
										.getChildText("x")));
								timeOSD.setY(Integer.parseInt(timeOSDE
										.getChildText("y")));
							} catch (Exception ex) {
								log.error(ex.getMessage());
							}
							/**
							 * 名称OSD信息
							 * 
							 * @author gaohe
							 * */
							OSDModel nameOSD = new OSDModel();
							temp.setNameOSD(nameOSD);
							nameOSD.setContent(channelNameOSDE
									.getChildText("content"));
							try {
								nameOSD.setEnableHide(Integer
										.parseInt(channelNameOSDE
												.getChildText("enableHide")));
								nameOSD.setX(Integer.parseInt(channelNameOSDE
										.getChildText("x")));
								nameOSD.setY(Integer.parseInt(channelNameOSDE
										.getChildText("y")));
							} catch (Exception ex) {
								log.error(ex.getMessage());
							}

							/**
							 * 追加OSD
							 * 
							 * @author gaohe
							 * */
							ArrayList<OSDModel> appendOSDList = new ArrayList<OSDModel>();
							for (int j = 0; j < appendOSDGroupE.getChildren()
									.size(); j++) {
								Element appendE = (Element) appendOSDGroupE
										.getChildren().get(i);
								OSDModel osd = new OSDModel();
								osd.setContent(appendE.getChildText("content"));
								try {
									osd.setEnableHide(Integer.parseInt(appendE
											.getChildText("enableHide")));
									osd.setX(Integer.parseInt(appendE
											.getChildText("x")));
									osd.setY(Integer.parseInt(appendE
											.getChildText("y")));
									osd.setId(Integer.parseInt(appendE.getChildText("pos")));
								} catch (Exception ex) {
									log.error(ex.getMessage());
								}

								appendOSDList.add(osd);
							}
							temp.setAppendOSDList(appendOSDList);
							cameraOSDList.add(temp);
						}
						//没有实现
						ebi.reportGetFDOSDResult(cameraOSDList);
					}
					// 查询设备通道OSD信息

					else if (cmd.trim().equalsIgnoreCase("GetCameraOSD")) {
						ArrayList<CameraOSDInfoModel> cameraOSDList = new ArrayList<CameraOSDInfoModel>();
						Element groupElement = paraEle.getChild("cameraOSDGroup");
						List children = groupElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);
							Element timeOSDE = e.getChild("timeOSD");
							Element channelNameOSDE = e
									.getChild("channelNameOSD");
							Element appendOSDGroupE = e
									.getChild("appendOSDGroup");
							String resCode = e.getChildText("resCode");
							String deviceCode = e.getChildText("deviceCode");
							String channelNum = e.getChildText("channelNum");

							CameraOSDInfoModel temp = new CameraOSDInfoModel();
							temp.setVideoCode(resCode);
							temp.setFdCode(deviceCode);
							temp.setChannelNum(channelNum);
							/**
							 * 时间OSD信息
							 * 
							 * @author gaohe
							 * */
							OSDModel timeOSD = new OSDModel();
							temp.setTimeOSD(timeOSD);
							timeOSD
									.setContent(timeOSDE
											.getChildText("content"));
							try {
								timeOSD.setEnableHide(Integer.parseInt(timeOSDE
										.getChildText("enableHide")));
								timeOSD.setX(Integer.parseInt(timeOSDE
										.getChildText("x")));
								timeOSD.setY(Integer.parseInt(timeOSDE
										.getChildText("y")));
							} catch (Exception ex) {
								log.error(ex.getMessage());
							}
							/**
							 * 名称OSD信息
							 * 
							 * @author gaohe
							 * */
							OSDModel nameOSD = new OSDModel();
							temp.setNameOSD(nameOSD);
							nameOSD.setContent(channelNameOSDE
									.getChildText("content"));
							try {
								nameOSD.setEnableHide(Integer
										.parseInt(channelNameOSDE
												.getChildText("enableHide")));
								nameOSD.setX(Integer.parseInt(channelNameOSDE
										.getChildText("x")));
								nameOSD.setY(Integer.parseInt(channelNameOSDE
										.getChildText("y")));
							} catch (Exception ex) {
								log.error(ex.getMessage());
							}

							/**
							 * 追加OSD
							 * 
							 * @author gaohe
							 * */
							ArrayList<OSDModel> appendOSDList = new ArrayList<OSDModel>();
							for (int j = 0; j < appendOSDGroupE.getChildren()
									.size(); j++) {
								Element appendE = (Element) appendOSDGroupE
										.getChildren().get(i);
								OSDModel osd = new OSDModel();
								osd.setContent(appendE.getChildText("content"));
								try {
									osd.setEnableHide(Integer.parseInt(appendE
											.getChildText("enableHide")));
									osd.setX(Integer.parseInt(appendE
											.getChildText("x")));
									osd.setY(Integer.parseInt(appendE
											.getChildText("y")));
									osd.setId(Integer.parseInt(appendE.getChildText("pos")));
								} catch (Exception ex) {
									log.error(ex.getMessage());
								}

								appendOSDList.add(osd);
							}
							temp.setAppendOSDList(appendOSDList);
							cameraOSDList.add(temp);
						}
						ebi.reportGetCameraOSDResult(cameraOSDList);
					}
				}

			}
		}

	}

	private  Document getDoc(byte byteXml[]) {
		Document ret = null;

		try {
			lock.lock();
			ret = saxBuilder.build(new ByteArrayInputStream(byteXml));

		} catch (Exception e) {
			e.printStackTrace();
		}

		lock.unlock();
		return ret;
	}

	/**
	 * @author ghj
	 * @说明：发送订阅信息
	 * */
	public  void orderMessage() {

		if(BootNettyChannelInboundHandlerAdapter.ctx!=null){

			// 发送订阅告警信息
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applyAlarm(serverCode));
			// 发送订阅设备通道上下线信息
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applyDeviceChannelOnlineState(serverCode));
			// 发送订阅上报录像结果
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applyCameraRecordFinished(serverCode));
			// 发送订阅上报图像抓拍结果
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applySnapPicFinished(serverCode));
			// 发送订阅设备上下线信息
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applyDeviceOnlineState(serverCode));
			// 发送订阅服务上下线信息
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.applyServiceOnlineState(serverCode));
		}

	}

	public  void register2CMS() {
		if(BootNettyChannelInboundHandlerAdapter.ctx!=null){

			// 发送订阅告警信息
			BootNettyChannelInboundHandlerAdapter.ctx.write(pro.register2CMS(serverCode, userName,password));
		}
	}

}

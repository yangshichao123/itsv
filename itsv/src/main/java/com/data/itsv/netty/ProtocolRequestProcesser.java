package com.data.itsv.netty;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import com.data.itsv.model.*;
import com.data.itsv.model.vo.VAlarmSetVo;
import com.data.itsv.model.vo.VAlarmVo;
import com.data.itsv.service.ProtocolService;
import com.data.itsv.util.DateUtils;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.UUIDHelper;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 协议处理器
 * 
 * @author ghj
 * */
@Slf4j
@Component
public class ProtocolRequestProcesser {
	// 加锁器
	private  ReentrantLock lock = new ReentrantLock();

	private  SAXBuilder saxBuilder = new SAXBuilder();
	// 协议服务类
	@Autowired
	private  ProtocolService ebi;

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
					// cms注册
					if (cmd.trim().equalsIgnoreCase("CSRegister")) {

						String cmsId = paraEle.getChildTextTrim("id");
						String userName = paraEle
								.getChildTextTrim("accountName");
						String password = paraEle.getChildTextTrim("password");
						ebi.register(rm, userName, password);

					}
					// cms保持心跳
					else if (cmd.trim().equalsIgnoreCase("KeepAlive")) {

						String cmsId = paraEle.getChildTextTrim("id");
						String keepAlivePeriod = paraEle
								.getChildTextTrim("keepAlivePeriod");
						ebi.keepAlive(rm, cmsId, keepAlivePeriod);
					}

					// 告警信息上报
					// 资源状态上报
					else if (cmd.trim().equalsIgnoreCase("ReportCameraState")) {
						ArrayList<AMQDeviceStateModel> list = new ArrayList<AMQDeviceStateModel>();
						Element groupElement = paraEle.getChild("group");
						List children = groupElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String resId = e.getChildText("resId");
							String state = e.getChildText("state");
							AMQDeviceStateModel temp = new AMQDeviceStateModel();
							// 告警资源ID

							temp.setResId(resId);

							// 状态

							temp.setStateS(state);
							if ("online".equals(temp.getStateS())) {
								temp.setStateI("1");
							} else {
								temp.setStateI("0");
							}

							list.add(temp);
						}

						ebi.reportCameraState(rm, list);
					}

					// 告警信息上报

					else if (cmd.trim().equalsIgnoreCase("ReportAlarm")) {
						ArrayList<Object> list = new ArrayList<>();
						List children = paraEle.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String id = e.getChildText("id");
							String sessionIds = e.getChildText("sessionId");
							String name = e.getChildText("name");
							String description = e.getChildText("description");
							String alarmType = e.getChildText("alarmType");
							String alarmLevel = e.getChildText("alarmLevel");
							String beginTime = e.getChildText("beginTime");
							String endTime = e.getChildText("endTime");
							String alarmState = e.getChildText("alarmState");
							String affirmMan = e.getChildText("affirmMan");
							String affirmTime = e.getChildText("affirmTime");
							String cleanMan = e.getChildText("cleanMan");
							String cleanTime = e.getChildText("cleanTime");
							VAlarm temp = new VAlarm();
							// 告警资源ID

							temp.setId(Integer.parseInt(id));

							// 告警唯一标识
							temp.setSessionid(sessionIds);

							// 告警源名称

							temp.setName(name);

							// 告警源描述

							temp.setDescription(description);

							// 告警类型

							temp.setAlarmType(alarmType);

							// 告警级别
							temp.setAlarmlevel(Integer.parseInt(alarmLevel) );

							// 告警开始时间
							temp.setBegintime(DateUtils.parseDate(beginTime,"yyyy-MM-dd HH:mm:ss"));
							// 告警编号
							temp.setAlarmid(temp.getId() + temp.getAlarmType()
									+ temp.getBegintime());

							// 告警结束时间

							temp.setEndtime(DateUtils.parseDate(endTime,"yyyy-MM-dd HH:mm:ss"));

							// 1：告警0：正常

							temp.setAlarmstate(Integer.parseInt(alarmState));

							// 确认人，用户的20位编码

							temp.setAffirmman(affirmMan);

							// 确认时间
							temp.setAffirm(DateUtils.parseDate(affirmTime,"yyyy-MM-dd HH:mm:ss"));
							// 清除人，用户的20位编码

							temp.setCleanman(cleanMan);

							// 清除时间

							temp.setCleantime(DateUtils.parseDate(cleanTime,"yyyy-MM-dd HH:mm:ss"));

							list.add(temp);

						}
						ebi.reportAlarm(rm, list);
					}
					// 获取目录树 我的目录
					else if (cmd.trim().equalsIgnoreCase("GetCatolog")) {

						String userId = paraEle.getChildTextTrim("id");
						String treeType = paraEle.getChildTextTrim("treeType");
						ebi.getCatolog(rm, userId, treeType);

					}
					// 获取目录资源
					else if (cmd.trim().equalsIgnoreCase("GetCatologRes")) {

						String userId = paraEle.getChildTextTrim("id");
						String catologId = paraEle
								.getChildTextTrim("catologId");
						String treeType = paraEle.getChildTextTrim("treeType");
						ebi.getCatologRes(rm, userId, catologId, treeType);

					}
					// 获取通道预置位
					else if (cmd.trim().equalsIgnoreCase("GetPtzPreset")) {

						String userId = paraEle.getChildTextTrim("id");
						String resId = paraEle.getChildTextTrim("resId");
						ebi.queryCameraPtzPreset(rm, userId, resId);

					}
					// 更新我的目录
					else if (cmd.trim().equalsIgnoreCase("ChangeCatolog")) {
						String optType = paraEle.getChildTextTrim("opt");
						String userId = paraEle.getChildTextTrim("id");
						String catologId = paraEle
								.getChildTextTrim("catologId");
						String name = paraEle.getChildTextTrim("name");
						String parentId = paraEle.getChildTextTrim("parentId");
						String desc = "";
						String treeType = paraEle.getChildTextTrim("treeType");
						ebi.updateMyCatolog(rm, optType, userId, catologId,
								name, parentId, desc, treeType);

					}
					// 更新我的目录资源
					else if (cmd.trim().equalsIgnoreCase("ChangeCatologRes")) {
						/*String optType = paraEle.getChildTextTrim("opt");
						String userId = paraEle.getChildTextTrim("id");
						String catologId = paraEle
								.getChildTextTrim("catologId");
						String treeType = paraEle.getChildTextTrim("treeType");
						Element groupElement = paraEle.getChild("camResGroup");
						ArrayList<Map> list = new ArrayList<Map>();
						List children = groupElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);
							ASObject as = new ASObject();
							as.put("IGroupId", catologId);
							as.put("code", e.getChildTextTrim("resId"));
							// as.put("type", type);
							// as.put("name", name);
							// as.put("state", state);
							// as.put("voiceId", voiceId);
							// as.put("ptzFlag", ptzFlag);
							// as.put("realCodeType", realCodeType);
							list.add(as);
						}
						ebi.updateMyCatologRes(rm, optType, userId, list,
								treeType);*/

					}

					// 获取我的轮巡组
					else if (cmd.trim().equalsIgnoreCase("GetTourGroup")) {
						String userId = paraEle.getChildTextTrim("id");
						ebi.getTourGroup(rm, userId);

					}
					// 获取我的轮巡组资源
					else if (cmd.trim().equalsIgnoreCase("GetTourGroupRes")) {
						String userId = paraEle.getChildTextTrim("id");
						String tourId = paraEle.getChildTextTrim("tourId");
						ebi.getTourGroupRes(rm, userId, tourId);

					}
					// 更新我的轮巡组
					else if (cmd.trim().equalsIgnoreCase("ChangeTourGroup")) {
						//暂时没有实现
						/*String optType = paraEle.getChildTextTrim("opt");
						String userId = paraEle.getChildTextTrim("id");
						Element groupTourElement = paraEle.getChild("tour");
						String tourId = groupTourElement
								.getChildTextTrim("tourId");
						if (tourId == "") {
							tourId = "0";
						}
						String screeNnum = groupTourElement
								.getChildTextTrim("screenNum");
						String name = groupTourElement.getChildTextTrim("name");
						String desc = groupTourElement.getChildTextTrim("desc");
						String interval = groupTourElement
								.getChildTextTrim("interval");
						Element groupCamElement = paraEle
								.getChild("camResGroup");
						ArrayList<Map> list = new ArrayList<Map>();
						List children = groupCamElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);
							ASObject as = new ASObject();
							as.put("code", e.getChildTextTrim("resId"));
							as.put("tourIndex", e.getChildTextTrim("resIndex"));
							as.put("tourId", tourId);
							list.add(as);
						}
						ebi.updateTourGroup(rm, optType, userId, tourId, name,
								desc, interval, screeNnum, list);*/

					}

					// 获取我的布局资源-zml
					else if (cmd.trim().equalsIgnoreCase("GetLayout")) {
						String cmsId = paraEle.getChildTextTrim("id");
						String layoutId = "";
						ebi.getLayout(rm, cmsId, layoutId);
					}
					// 更新我的布局-zml
					else if (cmd.trim().equalsIgnoreCase("ChangeLayout")) {
						String userId = paraEle.getChildTextTrim("id");
						String layoutId = paraEle.getChildTextTrim("layoutId");
						String layoutName = paraEle
								.getChildTextTrim("layoutName");
						String optType = paraEle.getChildTextTrim("opt");
						ebi.changeLayout(rm, userId, layoutId, layoutName,
								optType);
					}
					// 设置我的布局-zml
					else if (cmd.trim().equalsIgnoreCase("SetLayout")) {
						String userId = paraEle.getChildTextTrim("id");

						Element groupElement = paraEle.getChild("layoutGroup");
						Element layout = groupElement.getChild("layout");
						String layoutId = layout.getChildTextTrim("layoutId");
						// String
						// layoutName=groupElement.getChildTextTrim("layoutName");
						String screenNum = layout.getChildTextTrim("screenNum");
						Element camResGroup = layout.getChild("camResGroup");
						ArrayList<Map> list = new ArrayList<Map>();
						List cameraList = camResGroup.getChildren();
						for (int i = 0; i < cameraList.size(); i++) {
							Element e = (Element) cameraList.get(i);
							Map as = new HashMap();
							as.put("videoCode", e.getChildTextTrim("resId"));
							as.put("iLayoutId", layoutId);
							as.put("locationNum", e
									.getChildTextTrim("resIndex"));
							list.add(as);
						}
						ebi.setLayout(rm, userId, layoutId, screenNum, list);
					} else if (cmd.trim().equalsIgnoreCase("GetAlarmInfo")) {
						String userId = paraEle.getChildTextTrim("id");
						String alarmType = paraEle
								.getChildTextTrim("alarmType");
						String alarmState = paraEle
								.getChildTextTrim("alarmState");
						if (("-1").equals(alarmState)) {
							alarmState = "";
						}
						String pagesize = paraEle.getChildTextTrim("pageSize");
						String pageindex = paraEle
								.getChildTextTrim("pageIndex");
						ebi.getAlarmInfo(rm, userId, alarmType, alarmState,
								pagesize, pageindex);
					} else if (cmd.trim().equalsIgnoreCase("GetAlarmEvidence")) {
						String userId = paraEle.getChildTextTrim("id");
						String alarmId = paraEle.getChildTextTrim("alarmId");
						ebi.getAlarmEvidence(rm, userId, alarmId);
					} else if (cmd.trim().equalsIgnoreCase("ChangePtzPreset")) {
						String userId = paraEle.getChildTextTrim("id");
						String optType = paraEle.getChildTextTrim("opt");
						String resId = paraEle.getChildTextTrim("resId");
						Element groupCamElement = paraEle.getChild("preset");
						String name = groupCamElement.getChildTextTrim("name");
						String index = groupCamElement
								.getChildTextTrim("index");
						String keepWatch = groupCamElement
								.getChildTextTrim("keepWatch");
						String presetId = groupCamElement
								.getChildTextTrim("presetId");
						ebi.changePtzPreset(rm, optType, userId, resId, name,
								index, keepWatch, presetId);

					} else if (cmd.trim().equalsIgnoreCase("GetAlarmRes")) {
						String userId = paraEle.getChildTextTrim("id");
						ebi.getAlarmRes(rm, userId);
					}
					// 上报录像结果
					else if (cmd.trim().equalsIgnoreCase(
							"ReportCameraRecordFinished")) {

						Element groupElement = paraEle.getChild("recordGroup");
						List children = groupElement.getChildren();
						ArrayList list = new ArrayList();
						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String referenceId = e.getChildTextTrim("id");
							String fileUrl = e.getChildTextTrim("fileUrl");

							VEvidenceFile temp = new VEvidenceFile();
							temp.setFileType(2);
							temp.setId(UUIDHelper.getFileCode());
							temp.setReferenceId(referenceId);
							temp.setUrl(fileUrl);

							list.add(temp);
						}

						ebi.reportCameraRecordFinished(rm, list);
						{

							rootEle = null;
							list = null;
							children = null;
							groupElement = null;
							paraEle = null;
							rootEle = null;
							doc = null;
						}
					}
					// 上报抓拍结果
					else if (cmd.trim().equalsIgnoreCase(
							"ReportSnapPicFinished")) {

						Element groupElement = paraEle.getChild("snapPicGroup");
						List children = groupElement.getChildren();
						ArrayList list = new ArrayList();
						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String referenceId = e.getChildTextTrim("id");
							String fileUrl = e.getChildTextTrim("fileUrl");

							VEvidenceFile temp = new VEvidenceFile();
							temp.setFileType(1);
							temp.setId(UUIDHelper.getFileCode());
							temp.setReferenceId(referenceId);
							temp.setUrl(fileUrl);

							list.add(temp);
						}

						ebi.reportSnapPicFinished(rm, list);
						{

							rootEle = null;
							list = null;
							children = null;
							groupElement = null;
							paraEle = null;
							rootEle = null;
							doc = null;
						}
					}
					
					// 设备状态上报
					else if (cmd.trim().equalsIgnoreCase("ReportDeviceState")) {
						ArrayList<AMQDeviceStateModel> list = new ArrayList<AMQDeviceStateModel>();
						Element groupElement = paraEle.getChild("group");
						List children = groupElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String id = e.getChildText("id");
							String state = e.getChildText("state");
							AMQDeviceStateModel temp = new AMQDeviceStateModel();
							// 告警资源ID

							temp.setResId(id);

							// 状态

							temp.setStateS(state);
							if ("online".equals(temp.getStateS())) {
								temp.setStateI("1");
							} else {
								temp.setStateI("0");
							}

							list.add(temp);
						}

						ebi.reportDeviceState(rm, list);
					}
					// 服务状态上报
					else if (cmd.trim().equalsIgnoreCase("ReportServiceState")) {
						ArrayList<AMQDeviceStateModel> list = new ArrayList<AMQDeviceStateModel>();
						Element groupElement = paraEle.getChild("serviceStateGroup");
						List children = groupElement.getChildren();

						for (int i = 0; i < children.size(); i++) {
							Element e = (Element) children.get(i);

							String id = e.getChildText("serviceId");
							String state = e.getChildText("state");
							String time = e.getChildText("time");
							AMQDeviceStateModel temp = new AMQDeviceStateModel();
							// 告警资源ID

							temp.setResId(id);
                            temp.setTime(time);
							// 状态

							temp.setStateS(state);
							if ("online".equals(temp.getStateS())) {
								temp.setStateI("1");
							} else {
								temp.setStateI("0");
							}

							list.add(temp);
						}

						ebi.reportServiceState(rm, list);
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
	 * @说明：生产消息
	 * */
	public  void productMessage(AMQMessageModel amq) {

		// 发送到B/S客户端
		sendMessage2BSClient(amq);
		// 发送到CS客户端
		sendMessage2CSClient(amq);
	}
	// socket 发送到cs客户端
	public  void sendMessage2CSClient(AMQMessageModel amq) {

		HashMap userConnMap = HashMapHelper.getUserConnectionMap();
		Set set = userConnMap.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			if ("alarmInfomation".equals(amq.getType())) {
				ArrayList<VAlarmSetVo> userOrderAlarmList = HashMapHelper
						.getUserOrderAlarmMap().get(key);
				ArrayList preSendAlarmList = new ArrayList<VAlarmVo>();
				ArrayList<VAlarmVo> alarmList = amq.getList();
				for (int i = 0; i < alarmList.size(); i++) {
					for (int j = 0; j < userOrderAlarmList.size(); j++) {
						if ((alarmList.get(i).getId() + alarmList.get(i)
								.getAlarmType()).equals(userOrderAlarmList.get(
								j).getAlarmResId()
								+ userOrderAlarmList.get(j).getAlarmType())) {
							preSendAlarmList.add(alarmList.get(i));
						}
					}
				}
				// 发送
				ebi.reportCSAlarm((ChannelHandlerContext) userConnMap.get(key),
						preSendAlarmList);
				preSendAlarmList = null;
				alarmList = null;
				ebi = null;
			}
			// 设备状态更新
			else if ("cameraState".equals(amq.getType())) {
				ArrayList cameraStateList = amq.getList();
				// 发送
				ebi.reportCSCameraState((ChannelHandlerContext) userConnMap.get(key),
						cameraStateList);
			}
			// 视频通道删除
			else if ("cameraDelete".equals(amq.getType())) {
				ArrayList cameraList = amq.getList();
				// 发送
				ebi.reportCameraChanged((ChannelHandlerContext) userConnMap.get(key),
						cameraList, "1");
			}
			// 视频通道pag更改
			else if ("cameraDevicePagChange".equals(amq.getType())) {
				ArrayList cameraList = amq.getList();
				// 发送
				ebi.reportCameraChanged((ChannelHandlerContext) userConnMap.get(key),
						cameraList, "2");
			} else if ("alarmSetAdd".equals(amq.getType())) {
				ArrayList cameraList = amq.getList();
				// 发送
				ebi.reportCSAlarmChange((ChannelHandlerContext) userConnMap.get(key),
						cameraList, "1");
			} else if ("alarmSetUpdate".equals(amq.getType())) {
				ArrayList cameraList = amq.getList();
				// 发送
				ebi.reportCSAlarmChange((ChannelHandlerContext) userConnMap.get(key),
						cameraList, "3");
			} else if ("alarmSetDelete".equals(amq.getType())) {
				ArrayList cameraList = amq.getList();
				// 发送
				ebi.reportCSAlarmChange((ChannelHandlerContext) userConnMap.get(key),
						cameraList, "2");
			}
		}

	}

	public static void sendMessage2BSClient(AMQMessageModel amq) {

		/*String clientID = UUIDHelper.getUUID();

		AsyncMessage am = new AsyncMessage();
		am.setDestination("tick-data-feed");
		am.setHeader("DSSubtopic", "orderAlarm");
		am.setClientId(clientID);
		String msgID = UUIDUtils.createUUID();
		am.setMessageId(msgID);
		am.setTimestamp(System.currentTimeMillis());
		am.setBody(amq);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (getMessageBroker() != null) {
			getMessageBroker().routeMessageToService(am, null);
			System.out.println("~发送~" + df.format(new Date()) + "~");
		}
*/
	}
}

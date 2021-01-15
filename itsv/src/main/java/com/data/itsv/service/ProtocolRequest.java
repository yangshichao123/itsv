package com.data.itsv.service;

import com.data.itsv.model.AMQDeviceStateModel;
import com.data.itsv.model.NVRChannelInfoModel;
import com.data.itsv.model.VAlarm;
import com.data.itsv.model.VVideo;
import com.data.itsv.model.vo.VVideoVo;
import com.data.itsv.netty.vo.BaseMsg;

import java.util.ArrayList;


/**
 * @author ghj
 * @说明：该类用于对协议进行封装
 * */
public interface ProtocolRequest {
	/**
	 * @author ghj
	 * @说明 向CMS注册
	 * */
	public BaseMsg register2CMS(String serverCode, String userName, String password);

	/**
	 * @author ghj
	 * @说明 申请告警信息
	 * */
	public BaseMsg applyAlarm(String serverCode);
	/**
	 * @author ghj
	 * @说明 申请前端设备通道在线状态信息
	 * */
	public BaseMsg applyDeviceChannelOnlineState(String serverCode);
	/**
	 * @author ghj
	 * @说明 心跳
	 * */
	public BaseMsg keepAlive(String keepAlivePeriod);

	/**
	 * @author ghj
	 * @说明 存储计划变更
	 * */
	public BaseMsg changeStoragePlan(String opt,ArrayList<StroePlanModel> sp);
	/**
	 * @author ghj
	 * @说明 服务资源变更
	 * */
	public BaseMsg changeServiceResource(String opt,ArrayList<ServiceModel> sp);
	/**
	 * @author ghj
	 * @说明 设备资源更新变更
	 * */
	public BaseMsg changeDeviceResource(String opt,ArrayList<FDModel> sp);
	/**
	 * @author ghj
	 * @说明 镜头资源更新变更
	 * */
	public BaseMsg changeCamResource(String opt,ArrayList<VVideoVo> sp);
	/**
	 * @author ghj
	 * @说明 告警资源更新变更
	 * */
	public BaseMsg changeAlarmResource(String opt,ArrayList<AlarmResModel> sp);
	/**
	 * @author ghj
	 * @说明 语音资源更新变更
	 * */
	public BaseMsg changeVoiceResource(String opt,ArrayList<PhonicsModel> sp);
	/**
	 * @author ghj
	 * @说明 视频流上大屏
	 * */
	public BaseMsg sendVideo2ScreenSystem2Server( String videoCode, String decoderServerIp,String decoderServerPort,String ioIndexNumber);
	/**
	 * @author ghj
	 * @说明 申请订阅上报图像抓拍结果
	 * */
	public BaseMsg applySnapPicFinished(String code);
	/**
	 * @author ghj
	 * @说明 申请订阅上报录像结果
	 * */
	public BaseMsg applyCameraRecordFinished(String code);
	/**
	 * @说明 联动摄像机抓拍

	 * */

	public BaseMsg linkVideoSnapPic(String id,String videoCode);
	/**
	 * @说明 联动摄像机录像
	 * @beginTime 录像开始时间
	 * @endTime 录像停止时间
	 * */

	public BaseMsg linkVideoRecord(String id,String videoCode,String beginTime,String endTime);
	/**
	 * 停止录像联动摄像机

	 * */

	public BaseMsg requestStopRecord(String id,String videoCode);
	/**
	 * 重启设备

	 * */

	public BaseMsg requestRebootFd(ArrayList<String> fdCodes);
	/**
	 *获取设备从属关系(NVR和IPC关系)
	 *@author gaohe
	 * */

	public BaseMsg requestGetNVRChannelInfo(String fdCode);
	/**
	 *动态增加NVR设备通道（IPC）
	 *@author gaohe
	 * */

	public BaseMsg requestSetNVRChannelInfo(ArrayList<NVRChannelInfoModel> ipcList);
	/**
	 *查询摄像机OSD信息
	 *@author gaohe
	 *@param fdCode 设备编号
	 *@param channelNum 设备通道号 0(通道号，-1表示查询所有通道)
	 *@param videoCode 摄像机编号
	 * */

	public BaseMsg requestGetCameraOSD(String fdCode,String videoCode,String channelNum);
	/**
	 *查询NVR、DVR设备下摄像机OSD信息
	 *@author gaohe
	 *@param fdCode 设备编号
	 * */

	public BaseMsg requestGetFDOSD(String fdCode);
	/**
	 *设置摄像机OSD信息
	 *@author gaohe
	 *@param cameraOSDList 摄像机OSD信息
	 * */

	public BaseMsg requestSetCameraOSD(ArrayList<CameraOSDInfoModel> cameraOSDList);
	/**
	 *开始实时录像
	 *@author gaohe
	 *@param requestList 请求录像集合
	 * */

	public BaseMsg requestStartRecord4RealTimeVideo(ArrayList<VideoRecordFileModel> requestList);
	/**
	 *停止实时录像
	 *@author gaohe
	 *@param requestList 请求录像集合
	 * */

	public BaseMsg requestStopRecord4RealTimeVideo(ArrayList<VideoRecordFileModel> requestList);
	/**
	 * 上报告警字符串协议信息
	 * @author ghj
	 * @param alarm*/
	public String reportCSAlarm4StrProtocol(VAlarm alarm);
	/**
	 * 通知用户下线
	 * @author ghj
	 * */
	public String notifyUserLogout4StrProtocol(String userId);
	/**
	 * @author ghj
	 * @说明 申请前端设备在线状态信息
	 * */
	public BaseMsg applyDeviceOnlineState(String serverCode);
	/**
	 *服务重启
	 *@author gaohe
	 *@param serviceCodeList 服务编号列表
	 * */

	public BaseMsg rebootService(ArrayList<String> serviceCodeList);
	/**
	 * 设备状态变化
	 * @author ghj
	 * @param list 设备状态集合
	 * */
	public String reportDeviceState4StrProtocol(ArrayList<AMQDeviceStateModel> list);
	/**
	 * 摄像机状态变化
	 * @author ghj
	 * @param list 设备状态集合
	 * */
	public String reportCameraState4StrProtocol(ArrayList<AMQDeviceStateModel> list);
	/**
	 *
	 * 强迫用户下线
	 * @author ghj
	 *
	 * */
	public String forceTheUserLogout();
	/**
	 *动态修改NVR设备通道（IPC）
	 *@author gaohe
	 * */

	public BaseMsg requestUpdateNVRChannelInfo(ArrayList<NVRChannelInfoModel> ipcList);
	/**
	 *动态删除NVR设备通道（IPC）
	 *@author gaohe
	 * */

	public BaseMsg requestDeleteNVRChannelInfo(ArrayList<NVRChannelInfoModel> ipcList);
	/**
	 * @author ghj
	 * @说明 申请服务在线状态信息
	 * */
	public BaseMsg applyServiceOnlineState(String serverCode);
	/**
	 * 服务状态变化
	 * @author ghj
	 * @param list 服务状态集合
	 * */
	public String reportServiceState4StrProtocol(ArrayList<AMQDeviceStateModel> list);
	/**
	 * 上报用户上下线状态
	 * @author ghj
	 * @param userCode 用户编号
	 * @param state OnlineStateModel.ONLINE_STATE ,OnlineStateModel.OFFLINE_STATE
	 * */
	public String reportUserState4StrProtocol(String userCode,int state);
	/**
	 * 摄像机算法配置
	 * @author ghj
	 * */
	public BaseMsg changeVideoAlgorithm(int opt,ArrayList<VideoAlgorithmModel> list);
	/**
	 * 人脸信息变更
	 * @author ghj
	 * */
	public BaseMsg changeEmployee(String serverCode,int opt,ArrayList<EmployeeModel> list);
}

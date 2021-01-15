package com.data.itsv.service;

import com.data.itsv.model.AMQDeviceStateModel;
import com.data.itsv.model.CameraOSDInfoModel;
import com.data.itsv.model.RequestModel;
import com.data.itsv.model.VEvidenceFile;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProtocolService {
    /**
     * @author ghj
     * @说明 注册接口
     * @param
     * */
    public void register(RequestModel rm, String account, String password);

    /**
     * @author ghj
     * @说明 cms保持心跳接口
     * @param cmsId
     *            (20位编码)
     * */
    public void keepAlive(RequestModel rm, String cmsId, String keepAlivePeriod);

    /**
     * @author wym
     * @说明 上报通道状态
     * */
    public void reportCSCameraState(ChannelHandlerContext session, ArrayList<Object> list);

    /**
     * @author wym
     * @说明 上报告警信息
     * */
    public void reportCSAlarm(ChannelHandlerContext session, ArrayList<Object> list);

    /**
     * @author wym
     * @说明 上报资源更新
     * */
    public void reportCameraChanged(ChannelHandlerContext session, ArrayList<Object> list,
                                    String optType);

    /**
     * @author zml
     * @说明 获取我的布局资源
     * */
    public void getLayout(RequestModel rm, String id, String layoutId);

    /**
     * @author wym
     * @说明 获取目录 treeType 目录树类型0==用户目录树，1==我的目录树
     * */
    public void getCatolog(RequestModel rm, String useId, String treeType);

    /**
     * @author wym
     * @说明 获取目录资源
     * */
    public void getCatologRes(RequestModel rm, String useId, String catologId,
                              String treeType);

    /**
     * @author wym
     * @说明 获取通道预置位
     * */
    public void queryCameraPtzPreset(RequestModel rm, String useId, String resId);

    /**
     * @author wym
     * @说明 更新我的目录 optType :操作类型1==添加，2==删除，3==更新
     * */
    public void updateMyCatolog(RequestModel rm, String optType, String useId,
                                String catologId, String catologName, String parentCatologId,
                                String desc, String treeType);

    /**
     * @author wym
     * @说明 更新我的目录资源 totalPkt:资源总包数 pktNum:资源当前分包序号，从0开始 optType:操作类型1==添加，2==删除
     * */
    public void updateMyCatologRes(RequestModel rm, String optType,
                                   String useId, ArrayList<Map> list, String treeType);

    /**
     * @author wym
     * @说明：获取我的轮巡组
     * */
    public void getTourGroup(RequestModel rm, String useId);

    /**
     * @author wym
     * @说明 获取我的轮巡组资源
     * */
    public void getTourGroupRes(RequestModel rm, String useId, String tourId);

    /**
     * @author wym
     * @说明：更新我的轮巡组 optType:操作类型1==增，2==删，3==改
     * */
    public void updateTourGroup(RequestModel rm, String optType, String useId,
                                String tourId, String name, String descs, String interval,
                                String screen, ArrayList<Map> list);

    /**
     * @author ghj
     * @说明 修改我的布局资源
     * @optType 操作类型1==增，2==删，3==改
     * */
    public void changeLayout(RequestModel rm, String userId, String layoutId,
                             String layoutName, String optType);

    /**
     * @author zml
     * @说明 设置我的布局
     * @结果 0成功 1失败
     */
    public void setLayout(RequestModel rm, String userId, String layoutId,
                          String screenNum, ArrayList<Map> iLayoutList);

    /**
     * @author zml
     * @说明   获取告警信息
     * @结果 0成功 1失败
     */
    public void getAlarmInfo(RequestModel rm, String userId, String alarmType,
                             String alarmState, String pagesize, String pageindex);

    /**
     * @author zml
     * @说明  获取告警证据信息
     * @结果 0成功 1失败
     */
    public void getAlarmEvidence(RequestModel rm, String userId, String alarmId);

    /**
     * @author wym
     * @说明 更新预置位 optType :操作类型1==添加，2==删除，3==更新
     * */
    public void changePtzPreset(RequestModel rm, String optType, String useId,
                                String resId, String presetName, String presetIndex,
                                String keepWatch, String presetId);

    /**
     * @author ghj
     * @说明 上报通道状态
     * */
    public void reportCameraState(RequestModel rm, ArrayList<AMQDeviceStateModel> list);

    /**
     * @author ghj
     * @说明 上报告警信息
     * */
    public void reportAlarm(RequestModel rm, ArrayList<Object> list);

    /**
     * @author wym
     * @说明 获取告警联动等信息
     * */
    public void getAlarmRes(RequestModel rm, String userId);

    /**
     * @author wym
     * @说明 上报告警信息更改
     * */
    public void reportCSAlarmChange(ChannelHandlerContext session, ArrayList<Object> list,
                                    String optType);

    /**
     * @author ghj
     * @说明 上报告警信息
     * */
    public void reportCSAlarm4StrProtocol(ChannelHandlerContext session, ArrayList<Object> list);
    /**
     * @author wym
     * @说明 上报通道状态
     * */
    public void reportCSCameraState4StrProtocol(ChannelHandlerContext session, ArrayList<Object> list);
    /**
     * @author ghj
     * @说明 上报图片文件信息
     * */
    public void reportSnapPicFinished(RequestModel rm,ArrayList<VEvidenceFile> list);
    /**
     * @author ghj
     * @说明 上报录像文件信息
     * */
    public void reportCameraRecordFinished(RequestModel rm,ArrayList<VEvidenceFile> list);
    /**
     * 上报设备状态
     * @author ghj
     * */
    public void reportDeviceState(RequestModel rm, ArrayList<AMQDeviceStateModel> list);
    /**
     * 上报服务状态
     * @author ghj
     * */
    public void reportServiceState(RequestModel rm, ArrayList<AMQDeviceStateModel> list);

    /**
     * 上报nvr通道信息
     * @param deviceCode
     * @param resId
     * @param userName
     * @param password
     * @param ip
     * @param port
     */
    void reportNVRChannelInfo(String deviceCode, String resId, String userName, String password, String ip, String port);


    boolean reportGetFDOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList);

    boolean reportGetCameraOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList);
}

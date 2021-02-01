package com.data.itsv.service;

import com.data.itsv.model.AMQDeviceStateModel;
import com.data.itsv.model.VFactory;
import com.data.itsv.model.VMasterSlaveFd;
import com.data.itsv.model.VVideo;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.model.vo.VVideoVo;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public interface VideoService {
    /**
     * 上报摄像机状态
     * @param list
     */
    void reportCameraState(ArrayList<AMQDeviceStateModel> list);
     List<VVideo> getVideoByCode(String code);

    boolean getCameraOSD(String userCode, String fdCode,
                         String videoCode, String channelNum);

    /**
     * 根据用户 目录id 获取摄像机资源
     * @param useId
     * @param catologId
     * @param s
     * @return
     */
    String getVideoByUidDirId(String useId, String catologId, String s);

    /**
     * 通过我的目录编号 查询该目录下所能看到的视频资源
     * @param useId
     * @param catologId
     * @return
     */
    String getVideoByIGoupId(String useId, String catologId);

    /**
     * 获取我得巡视组 资源
     * @param useId
     * @param tourId
     * @return
     */
    String getVideoByTourId(String useId, String tourId);

    String addVideoIn(String name, String fdCode, String channelNum,
                      String desc, String bLocal, String reserve);
     void noticeChangeCamResource(String opt, ArrayList<VVideoVo> list);

    PageInfo<VVideoVo> getVideo(String userId, String code,
                                String name, String type, String fdCode, String pageSize,
                                String pageIndex);

     boolean updateVideo(String userId, String code,
                               String cameraTypeCode, String name, String address,
                               String longitude, String latitude, String descs, String fdCode,
                               String codeFormat, String analysistype, String realRes,
                               String realCodeType, String realImageQuality, String realCodeModel,
                               String realTransModel, String recRes, String recCodeType,
                               String recImageQuality, String recCodeModel, String recTransModel,
                               String target, String vChannel, String aChannel, String ptz,
                               String ptzProtocol, String osd, String bLocal, String showOsd,
                               String osdX, String osdY, String showTime, String timeX,
                               String timeY, String showRealStatus, String realStatusX,
                               String realStatusY, String showRecStatus, String recStatusX,
                               String recStatusY, String showAnalysisStatus,
                               String analysisStatusX, String analysisStatusY, String vssCode);

    boolean deleteVideoAllAppendOSD(String userCode, String videoCode);

    boolean addVideoAppendOSD(String userCode, String videoCode,
                              String content, String x, String y, String pos, String enableHide);

    boolean updateVideo2CMS(String userId, String code,
                           String cameraTypeCode, String name, String address,
                           String longitude, String latitude, String descs, String fdCode,
                           String codeFormat, String analysistype, String realRes,
                           String realCodeType, String realImageQuality, String realCodeModel,
                           String realTransModel, String recRes, String recCodeType,
                           String recImageQuality, String recCodeModel, String recTransModel,
                           String target, String vChannel, String aChannel, String ptz,
                           String ptzProtocol, String osd, String bLocal, String showOsd,
                           String osdX, String osdY, String showTime, String timeX,
                           String timeY, String showRealStatus, String realStatusX,
                           String realStatusY, String showRecStatus, String recStatusX,
                           String recStatusY, String showAnalysisStatus,
                           String analysisStatusX, String analysisStatusY, String vssCode);

    boolean mergeMasterSlaveFd(String userId, String fdCode,
                               String slaveFdChannelCode, String channelNum,
                               String slaveFDAccount, String slaveFDPassword, String slaveFDIP,
                               String slaveFDPort);

    boolean deleteMasterSlaveFd(String userId, String slaveFdChannelCode);

    ArrayList<VMasterSlaveFd> getMasterSlaveFd(String userId,
                                               String slaveFdChannelCode);

    boolean updateMasterSlaveFd(String userId, String fdCode, String channelNum, String slaveFdChannelCode,

                                String slaveFDAccount, String slaveFDPassword, String slaveFDIP, String slaveFDPort);
     boolean addMasterSlaveFd(String userId, String fdCode, String slaveFdChannelCode,
                                     String channelNum, String slaveFDAccount,
                                     String slaveFDPassword, String slaveFDIP, String slaveFDPort);

    /**
     * 删除摄像机通道 根据设备编号
     * @param code
     */
    void deleteVideoByFDCode(String code);
}

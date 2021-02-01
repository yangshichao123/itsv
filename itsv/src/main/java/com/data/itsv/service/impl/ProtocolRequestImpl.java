package com.data.itsv.service.impl;

import com.data.itsv.model.*;
import com.data.itsv.model.vo.VVideoVo;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.util.ProtocolWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProtocolRequest")
@Slf4j
public class ProtocolRequestImpl implements ProtocolRequest {
    /**
     * @author ghj
     * @说明：xml头
     * */
    private static String XMLHEADER = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>";

    @Override
    public BaseMsg register2CMS(String serverCode, String userName,
                                String password) {
        // TODO Auto-generated method stub

        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='Register'>");
        sf.append("<parameters>");
        sf.append("<id>" + serverCode + "</id>");
        sf.append("<username>" + userName + "</username>");
        sf.append("<password>" + password + "</password>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg applyAlarm(String serverCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplyAlarm'>");
        sf.append("<parameters>");
        sf.append("<id>" + serverCode + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg applyDeviceChannelOnlineState(String serverCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplyDeviceChannelOnlineState'>");
        sf.append("<parameters>");
        sf.append("<id>" + serverCode + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg keepAlive(String keepAlivePeriod) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='KeepAlive'>");
        sf.append("<parameters>");
        sf.append("<keepAlivePeriod>" + keepAlivePeriod + "</keepAlivePeriod>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    /*@Override
    public BaseMsg changeStoragePlan(String opt, ArrayList<StroePlanModel> list) {
        StringBuffer sf = new StringBuffer();

        // totalPackage
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request command='ChangeStoragePlan'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("<parameters>");
            sf.append("<VssCamera/>");
            sf.append("</parameters>");
            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);

                sf.append("<request command='ChangeStoragePlan'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");

                sf.append("<VssCamera>");

                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    StroePlanModel temp = list.get(i);
                    sf.append("<URL>");
                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");
                    sf.append("<resId>");
                    sf.append(temp.getResId());
                    sf.append("</resId>");
                    sf.append("<pid>");
                    sf.append(temp.getpId());
                    sf.append("</pid>");
                    sf.append("<type>");
                    sf.append(temp.getType());
                    sf.append("</type>");
                    sf.append("<day>");
                    sf.append(temp.getDay());
                    sf.append("</day>");
                    sf.append("<frameRate>");
                    sf.append(temp.getFrameRate());
                    sf.append("</frameRate>");
                    sf.append("<cycle>");
                    sf.append(temp.getCycle());
                    sf.append("</cycle>");
                    sf.append("<startTime>");
                    sf.append(temp.getStartTime());
                    sf.append("</startTime>");
                    sf.append("<endTime>");
                    sf.append(temp.getEndTime());
                    sf.append("</endTime>");
                    sf.append("<pagIp>");
                    sf.append(temp.getPagIp());
                    sf.append("</pagIp>");
                    sf.append("<pagPort>");
                    sf.append(temp.getPagPort());
                    sf.append("</pagPort>");
                    sf.append("<vtduIp>");
                    sf.append(temp.getVtduIp());
                    sf.append("</vtduIp>");
                    sf.append("<vtduPort>");
                    sf.append(temp.getVtduPort());
                    sf.append("</vtduPort>");
                    sf.append("</URL>");

                }

                sf.append("</VssCamera>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }
*/
   /* @Override
    public BaseMsg changeServiceResource(String opt,
                                         ArrayList<ServiceModel> list) {
        StringBuffer sf = new StringBuffer();

        // totalPackage
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request command='ChangeServiceResource'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("<parameters>");
            sf.append("<ServiceGroup/>");
            sf.append("</parameters>");
            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);

                sf.append("<request command='ChangeServiceResource'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");

                sf.append("<ServiceGroup>");

                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    ServiceModel temp = list.get(i);
                    sf.append("<URL>");
                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");
                    sf.append("<code>");
                    sf.append(temp.getCode());
                    sf.append("</code>");
                    sf.append("<parentServiceCode>");
                    sf.append(temp.getParentCode());
                    sf.append("</parentServiceCode>");
                    sf.append("<name>");
                    sf.append(temp.getName());
                    sf.append("</name>");
                    sf.append("<descs>");
                    sf.append(temp.getDesc());
                    sf.append("</descs>");
                    sf.append("<type>");
                    sf.append(temp.getType());
                    sf.append("</type>");
                    sf.append("<ip>");
                    sf.append(temp.getIp());
                    sf.append("</ip>");
                    sf.append("<port>");
                    sf.append(temp.getAcceptPort());
                    sf.append("</port>");
                    sf.append("<extField>");
                    sf.append(temp.getExtField());
                    sf.append("</extField>");
                    sf.append("</URL>");

                }

                sf.append("</ServiceGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/

    @Override
    public BaseMsg changeDeviceResource(String opt, List<VFdevice> list) {
        StringBuffer sf = new StringBuffer();

        // totalPackage
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request command='ChangeDeviceResource'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("<parameters>");
            sf.append("<DeviceGroup/>");
            sf.append("</parameters>");

            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);

                sf.append("<request command='ChangeDeviceResource'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");

                sf.append("<DeviceGroup>");

                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    VFdevice temp = list.get(i);
                    sf.append("<URL>");
                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");
                    sf.append("<code>");
                    sf.append(temp.getCode());
                    sf.append("</code>");

                    sf.append("<name>");
                    sf.append(temp.getName());
                    sf.append("</name>");

                    sf.append("<fdtempleteCode>");
                    sf.append(temp.getFdtempleteId());
                    sf.append("</fdtempleteCode>");

                    sf.append("<account>");
                    sf.append(temp.getAccount());
                    sf.append("</account>");

                    sf.append("<password>");
                    sf.append(temp.getPassword());
                    sf.append("</password>");

                    sf.append("<ip>");
                    sf.append(temp.getIp());
                    sf.append("</ip>");

                    sf.append("<port>");
                    sf.append(temp.getPort());
                    sf.append("</port>");

                    sf.append("<serverCode>");
                    sf.append(temp.getServerCode());
                    sf.append("</serverCode>");

                    sf.append("<typeCode>");
                    sf.append(temp.getCode().substring(10,13));
                    sf.append("</typeCode>");

                    sf.append("</URL>");

                }

                sf.append("</DeviceGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }
    @Override
    public BaseMsg changeCamResource(String opt, ArrayList<VVideoVo> list) {
        StringBuffer sf = new StringBuffer();

        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request  command='ChangeCamResource'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(totalPackage);
            sf.append("</totalPkt>");

            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");

            sf.append("<parameters>");
            sf.append("<CamGroup/>");
            sf.append("</parameters>");
            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);
                sf.append("<request command='ChangeCamResource'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");

                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");
                sf.append("<CamGroup>");

                // 遍历结果集
                for (int i = 0; i < packageSize; i++) {
                    if (list.size() <= (j * packageSize + i)) {
                        break;
                    }
                    VVideoVo temp = list.get(j * packageSize + i);
                    sf.append("<URL>");
                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");

                    sf.append("<cameraCode>");
                    sf.append(temp.getCode());
                    sf.append("</cameraCode>");

                    sf.append("<name>");
                    sf.append(temp.getName());
                    sf.append("</name>");

                    sf.append("<devCode>");
                    sf.append(temp.getFdCode());
                    sf.append("</devCode>");

                    sf.append("<pagCode>");
                    sf.append(temp.getPagCode());
                    sf.append("</pagCode>");

                    sf.append("<vtduCode>");
                    sf.append(temp.getVtduCode());
                    sf.append("</vtduCode>");

                    sf.append("<cmsCode>");
                    sf.append(temp.getCmsCode());
                    sf.append("</cmsCode>");

                    sf.append("<vssCode>");
                    sf.append(temp.getVssCode());
                    sf.append("</vssCode>");

                    sf.append("<analysisType>");
                    sf.append(temp.getAnalysistype());
                    sf.append("</analysisType>");

                    sf.append("<mainStmRes>");
                    sf.append(temp.getMainRes());
                    sf.append("</mainStmRes>");

                    sf.append("<mainStmQuality>");
                    sf.append(temp.getMainImageQuality());
                    sf.append("</mainStmQuality>");

                    sf.append("<mainStmCodeModle>");
                    sf.append(temp.getMainCodeModel());
                    sf.append("</mainStmCodeModle>");

                    sf.append("<mainStmTransModle>");
                    sf.append(temp.getMainTransModel());
                    sf.append("</mainStmTransModle>");

                    sf.append("<childStmRes>");
                    sf.append(temp.getChildRes());
                    sf.append("</childStmRes>");

                    sf.append("<childStmQuality>");
                    sf.append(temp.getChildImageQuality());
                    sf.append("</childStmQuality>");

                    sf.append("<childStmCodeModle>");
                    sf.append(temp.getChildCodeModel());
                    sf.append("</childStmCodeModle>");

                    sf.append("<childStmTransModle>");
                    sf.append(temp.getChildTransModel());
                    sf.append("</childStmTransModle>");

                    sf.append("<vChannel>");
                    sf.append(temp.getVchannel());
                    sf.append("</vChannel>");

                    sf.append("<aChannel>");
                    sf.append(temp.getAchannel());
                    sf.append("</aChannel>");

                    sf.append("<ptz>");
                    sf.append(temp.getPtz());
                    sf.append("</ptz>");

                    sf.append("<ptzProtocal>");
                    sf.append(temp.getPtzprotocol());
                    sf.append("</ptzProtocal>");

                    sf.append("<isLocal>");
                    sf.append(temp.getIsLocal());
                    sf.append("</isLocal>");

                    sf.append("<osdName>");
                    sf.append(temp.getOsd());
                    sf.append("</osdName>");

                    sf.append("<latitude>");
                    sf.append(temp.getLatitude());
                    sf.append("</latitude>");

                    sf.append("<longitude>");
                    sf.append(temp.getLongitude());
                    sf.append("</longitude>");

                    sf.append("<showOsd>");
                    sf.append(temp.getShowosd());
                    sf.append("</showOsd>");

                    sf.append("<osdX>");
                    sf.append(temp.getOsdx());
                    sf.append("</osdX>");

                    sf.append("<osdY>");
                    sf.append(temp.getOsdy());
                    sf.append("</osdY>");

                    sf.append("<showTime>");
                    sf.append(temp.getShowtime());
                    sf.append("</showTime>");

                    sf.append("<timeX>");
                    sf.append(temp.getTimex());
                    sf.append("</timeX>");

                    sf.append("<timeY>");
                    sf.append(temp.getTimey());
                    sf.append("</timeY>");

                    sf.append("<mainStmBitrate>");
                    sf.append(temp.getShowrealstatus());
                    sf.append("</mainStmBitrate>");

                    sf.append("<mainStmFrameRate>");
                    sf.append(temp.getRealstatusx());
                    sf.append("</mainStmFrameRate>");

                    sf.append("<mainStmIFrameInterval>");
                    sf.append(temp.getRealstatusy());
                    sf.append("</mainStmIFrameInterval>");

                    sf.append("<childStmBitrate>");
                    sf.append(temp.getShowrecstatus());
                    sf.append("</childStmBitrate>");

                    sf.append("<childEnable>");
                    sf.append("");
                    sf.append("</childEnable>");

                    sf.append("<childStmFrameRate>");
                    sf.append(temp.getRecstatusx());
                    sf.append("</childStmFrameRate>");

                    sf.append("<childStmIFrameInterval>");
                    sf.append(temp.getRecstatusy());
                    sf.append("</childStmIFrameInterval>");

                    sf.append("<showAnalysisStatus>");
                    sf.append(temp.getShowanalysisstatus());
                    sf.append("</showAnalysisStatus>");

                    sf.append("<analysisStatusX>");
                    sf.append(temp.getAnalysisstatusx());
                    sf.append("</analysisStatusX>");

                    sf.append("<analysisStatusY>");
                    sf.append(temp.getAnalysisstatusy());
                    sf.append("</analysisStatusY>");

                    sf.append("<codeFormat>");
                    sf.append(temp.getCodeformat());
                    sf.append("</codeFormat>");

                    sf.append("<deviceTypeCode>");
                    sf.append(temp.getFdTypeCode());
                    sf.append("</deviceTypeCode>");

                    sf.append("<channelIP>");
                    if (!"1".equals(temp.getChannelType())) {
                        sf.append(temp.getChannelIp());
                    }
                    sf.append("</channelIP>");

                    sf.append("<channelType>");
                    sf.append(temp.getChannelType());
                    sf.append("</channelType>");

                    sf.append("<reserve>");
                    sf.append(temp.getReserve());
                    sf.append("</reserve>");

                    sf.append("</URL>");

                }

                sf.append("</CamGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

   /* @Override
    public BaseMsg changeAlarmResource(String opt, ArrayList<AlarmResModel> list) {

        StringBuffer sf = new StringBuffer();
        // totalPackage
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request command='ChangeAlarmResource'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("<parameters>");
            sf.append("<AlarmResGroup/>");
            sf.append("</parameters>");

            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);

                sf.append("<request command='ChangeAlarmResource'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");
                sf.append("<AlarmResGroup>");
                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    AlarmResModel temp = list.get(i);
                    sf.append("<URL>");

                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");

                    sf.append("<id>");
                    sf.append(temp.getResId());
                    sf.append("</id>");

                    sf.append("<deviceId>");
                    sf.append(temp.getDeviceId());
                    sf.append("</deviceId>");

                    sf.append("<auId>");
                    sf.append(temp.getAuCode());
                    sf.append("</auId>");

                    sf.append("<pagId>");
                    sf.append(temp.getPagCode());
                    sf.append("</pagId>");

                    sf.append("<type>");
                    sf.append(temp.getAlarmType());
                    sf.append("</type>");

                    sf.append("<name>");
                    sf.append(temp.getName());
                    sf.append("</name>");

                    sf.append("<channelNum>");
                    sf.append(temp.getChannelNum());
                    sf.append("</channelNum>");

                    sf.append("<description>");
                    sf.append(temp.getDesc());
                    sf.append("</description>");

                    sf.append("<level>");
                    sf.append(temp.getLevel());
                    sf.append("</level>");

                    sf.append("<isLinkMap>");
                    sf.append(temp.getIsLinkMap());
                    sf.append("</isLinkMap>");

                    sf.append("<isArming>");
                    sf.append(temp.getIsArming());
                    sf.append("</isArming>");

                    sf.append("<isLinkSoud>");
                    sf.append(temp.getIsLinkSoud());
                    sf.append("</isLinkSoud>");

                    sf.append("<Ip>");
                    sf.append(temp.getAlarmFDIp());
                    sf.append("</Ip>");

                    sf.append("<Port>");
                    sf.append(temp.getAlarmFDPort());
                    sf.append("</Port>");

                    sf.append("<UserName>");
                    sf.append(temp.getAlarmFDUserName());
                    sf.append("</UserName>");

                    sf.append("<PassWord>");
                    sf.append(temp.getAlarmFDPassWord());
                    sf.append("</PassWord>");

                    sf.append("<latitude>");
                    sf.append(temp.getLatitude());
                    sf.append("</latitude>");

                    sf.append("<longitude>");
                    sf.append(temp.getLongitude());
                    sf.append("</longitude>");

                    // 布控信息
                    {

                        ArrayList armingList = temp.getArmingList();
                        for (int ai = 0; ai < armingList.size(); ai++) {
                            ArmingModel am = (ArmingModel) armingList.get(ai);
                            sf.append("<Arming>");
                            sf.append("<startTime>");
                            sf.append(am.getStartTime());
                            sf.append("</startTime>");
                            sf.append("<endTime>");
                            sf.append(am.getEndTime());
                            sf.append("</endTime>");
                            sf.append("</Arming>");
                        }

                    }
                    // 告警联动信息
                    {

                        ArrayList linkList = temp.getLinkList();
                        for (int li = 0; li < linkList.size(); li++) {
                            LinkModel lm = (LinkModel) linkList.get(li);
                            sf.append("<Link>");
                            sf.append("<resId>");
                            sf.append(lm.getResId());
                            sf.append("</resId>");
                            sf.append("<isLinkPtz>");
                            sf.append(lm.getIsLinkPtz());
                            sf.append("</isLinkPtz>");
                            sf.append("<ptzPresent>");
                            sf.append(lm.getPtzPresent());
                            sf.append("</ptzPresent>");
                            sf.append("<isLinkRec>");
                            sf.append(lm.getIsLinkRec());
                            sf.append("</isLinkRec>");
                            sf.append("<recFps>");
                            sf.append(lm.getRecFps());
                            sf.append("</recFps>");
                            sf.append("<isLinkPrev>");
                            sf.append(lm.getIsLinkPrev());
                            sf.append("</isLinkPrev>");
                            sf.append("<isLinkPic>");
                            sf.append(lm.getIsLinkPic());
                            sf.append("</isLinkPic>");
                            sf.append("</Link>");
                        }

                    }
                    sf.append("</URL>");

                }

                sf.append("</AlarmResGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/

   /* @Override
    public BaseMsg changeVoiceResource(String opt, ArrayList<PhonicsModel> list) {
        StringBuffer sf = new StringBuffer();

        // totalPackage
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }
        // 分包
        if (totalPackage == 0) {
            // 构造返回命令
            sf.append(XMLHEADER);

            sf.append("<request command='ChangeVoiceResource'>");

            sf.append("<parameters>");

            sf.append("<totalPkt>");
            sf.append(0);
            sf.append("</totalPkt>");
            sf.append("<pktNum>");
            sf.append(0);
            sf.append("</pktNum>");
            sf.append("<parameters>");
            sf.append("<VssCamera/>");
            sf.append("</parameters>");
            sf.append("</request>");
        }
        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {

                // 构造返回命令
                sf.append(XMLHEADER);

                sf.append("<request command='ChangeVoiceResource'>");

                sf.append("<parameters>");

                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");

                sf.append("<VoiceGroup>");

                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    PhonicsModel temp = list.get(j * packageSize + i);
                    sf.append("<URL>");
                    sf.append("<opt>");
                    sf.append(opt);
                    sf.append("</opt>");
                    sf.append("<voiceId>");
                    sf.append(temp.getCode());
                    sf.append("</voiceId>");

                    sf.append("<name>");
                    sf.append(temp.getName());
                    sf.append("</name>");

                    sf.append("<devCode>");
                    sf.append(temp.getFdCode());
                    sf.append("</devCode>");

                    sf.append("<pagCode>");
                    sf.append(temp.getPagCode());
                    sf.append("</pagCode>");

                    sf.append("<vtduCode>");
                    sf.append(temp.getVtduCode());
                    sf.append("</vtduCode>");

                    sf.append("<cmsCode>");
                    sf.append(temp.getCmsCode());
                    sf.append("</cmsCode>");

                    sf.append("<assCode>");
                    sf.append(temp.getAssCode());
                    sf.append("</assCode>");

                    sf.append("<channelIndex>");
                    sf.append(temp.getNum());
                    sf.append("</channelIndex>");

                    sf.append("<latitude>");
                    sf.append(temp.getLatitude());
                    sf.append("</latitude>");

                    sf.append("<longitude>");
                    sf.append(temp.getLongitude());
                    sf.append("</longitude>");

                    sf.append("</URL>");

                }

                sf.append("</VoiceGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/

    @Override
    public BaseMsg sendVideo2ScreenSystem2Server(String videoCode,
                                                 String decoderServerIp, String decoderServerPort,
                                                 String ioIndexNumber) {
        // TODO Auto-generated method stub
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='SendVideo2ScreenSystem'>");
        sf.append("<parameters>");
        sf.append("<videoCode>" + videoCode + "</videoCode>");
        sf.append("<decoderServerIp>" + decoderServerIp + "</decoderServerIp>");
        sf.append("<decoderServerPort>" + decoderServerPort
                + "</decoderServerPort>");
        sf.append("<ioIndexNumber>" + ioIndexNumber + "</ioIndexNumber>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg applyCameraRecordFinished(String code) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplyCameraRecordFinished'>");
        sf.append("<parameters>");
        sf.append("<id>" + code + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg applySnapPicFinished(String code) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplySnapPicFinished'>");
        sf.append("<parameters>");
        sf.append("<id>" + code + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg linkVideoRecord(String id, String videoCode,
                                   String beginTime, String endTime) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='RequestStartRecord'>");
        sf.append("<parameters>");
        sf.append("<id>" + id + "</id>");
        sf.append("<cameraId>" + videoCode + "</cameraId>");
        sf.append("<beginTime>" + beginTime + "</beginTime>");
        sf.append("<endTime>" + endTime + "</endTime>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg linkVideoSnapPic(String id, String videoCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='SnapPic'>");
        sf.append("<parameters>");
        sf.append("<id>" + id + "</id>");
        sf.append("<cameraId>" + videoCode + "</cameraId>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestStopRecord(String id, String videoCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='RequestStopRecord'>");
        sf.append("<parameters>");
        sf.append("<id>" + id + "</id>");
        sf.append("<cameraId>" + videoCode + "</cameraId>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestRebootFd(ArrayList<String> fdCodes) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='RebootFd'>");
        sf.append("<parameters>");
        sf.append("<fdGroup>");
        for (String fdCode : fdCodes) {
            sf.append("<url>");
            sf.append("<deviceCode>" + fdCode + "</deviceCode>");
            sf.append("</url>");
        }
        sf.append("</fdGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestGetNVRChannelInfo(String fdCode) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='GetNVRChannelInfo'>");
        sf.append("<parameters>");

        sf.append("<deviceCode>" + fdCode + "</deviceCode>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestSetNVRChannelInfo(
            ArrayList<NVRChannelInfoModel> ipcList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='SetNVRChannelInfo'>");
        sf.append("<parameters>");
        sf.append("<channelInfoGroup>");
        for (NVRChannelInfoModel temp : ipcList) {
            sf.append("<url>");
            sf
                    .append("<deviceCode>" + temp.getParentFdCode()
                            + "</deviceCode>");
            sf.append("<ip>" + temp.getIp() + "</ip>");
            sf.append("<port>" + temp.getPort() + "</port>");
            sf.append("<userName>" + temp.getAccount() + "</userName>");
            sf.append("<password>" + temp.getPassword() + "</password>");
            sf.append("<channelNum>" + temp.getChannelNum() + "</channelNum>");
            sf.append("<resId>" + temp.getChannelCode() + "</resId>");
            sf.append("</url>");
        }
        sf.append("</channelInfoGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestGetCameraOSD(String fdCode, String videoCode,
                                       String channelNum) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='GetCameraOSD'>");
        sf.append("<parameters>");

        sf.append("<deviceCode>" + fdCode + "</deviceCode>");
        sf.append("<resCode>" + videoCode + "</resCode>");
        sf.append("<channelNum>" + channelNum + "</channelNum>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestGetFDOSD(String fdCode) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='GetFDOSD'>");
        sf.append("<parameters>");

        sf.append("<deviceCode>" + fdCode + "</deviceCode>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }
/*
    @Override
    public BaseMsg requestSetCameraOSD(
            ArrayList<CameraOSDInfoModel> cameraOSDList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='SetCameraOSD'>");
        sf.append("<parameters>");
        sf.append("<cameraOSDGroup>");
        for (CameraOSDInfoModel cameraOSD : cameraOSDList) {
            sf.append("<url>");
            sf.append("<deviceCode>" + cameraOSD.getFdCode() + "</deviceCode>");
            sf.append("<resCode>" + cameraOSD.getVideoCode() + "</resCode>");
            sf.append("<channelNum>" + cameraOSD.getChannelNum()
                    + "</channelNum>");
            *//******************* begin*******时间OSD *********************************//*
            sf.append("<timeOSD>");
            if (cameraOSD.getTimeOSD() != null) {
                sf.append("<enableHide>"
                        + cameraOSD.getTimeOSD().getEnableHide()
                        + "</enableHide>");
                sf.append("<x>" + cameraOSD.getTimeOSD().getX() + "</x>");
                sf.append("<y>" + cameraOSD.getTimeOSD().getY() + "</y>");

                if (cameraOSD.getTimeOSD().getContent() != null) {
                    sf.append("<content>" + cameraOSD.getTimeOSD().getContent()
                            + "</content>");
                }
            } else {
                sf.append("<enableHide>1</enableHide>");
                sf.append("<x>0</x>");
                sf.append("<y>0</y>");
                sf.append("<content></content>");
            }

            sf.append("</timeOSD>");
            *//******************* end*******时间OSD *********************************//*

            *//******************* begin*******名称OSD *********************************//*
            sf.append("<channelNameOSD>");
            if (cameraOSD.getNameOSD() != null) {
                sf.append("<enableHide>"
                        + cameraOSD.getNameOSD().getEnableHide()
                        + "</enableHide>");
                sf.append("<x>" + cameraOSD.getNameOSD().getX() + "</x>");
                sf.append("<y>" + cameraOSD.getNameOSD().getY() + "</y>");

                if (cameraOSD.getNameOSD().getContent() != null) {
                    sf.append("<content>" + cameraOSD.getNameOSD().getContent()
                            + "</content>");
                }
            } else {
                sf.append("<enableHide>1</enableHide>");
                sf.append("<x>0</x>");
                sf.append("<y>0</y>");
                sf.append("<content></content>");
            }

            sf.append("</channelNameOSD>");
            *//******************* end*******名称OSD *********************************//*

            *//******************* begin*******追加OSD *********************************//*
            sf.append("<appendOSDGroup>");
            if (cameraOSD != null && cameraOSD.getAppendOSDList() != null) {
                for (OSDModel osd : cameraOSD.getAppendOSDList()) {
                    sf.append("<osd>");
                    sf.append("<pos>" + osd.getId() + "</pos>");
                    sf.append("<enableHide>" + osd.getEnableHide()
                            + "</enableHide>");
                    sf.append("<x>" + osd.getX() + "</x>");
                    sf.append("<y>" + osd.getY() + "</y>");

                    if (osd.getContent() != null) {
                        sf
                                .append("<content>" + osd.getContent()
                                        + "</content>");
                    }

                    sf.append("</osd>");
                }
            }
            sf.append("</appendOSDGroup>");

            *//******************* end*******追加OSD *********************************//*

            sf.append("</url>");
        }
        sf.append("</cameraOSDGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/

  /*  @Override
    public BaseMsg requestStartRecord4RealTimeVideo(
            ArrayList<VideoRecordFileModel> requestList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='StartRecord4RealTimeVideo'>");
        sf.append("<parameters>");
        sf.append("<channelInfoGroup>");
        for (VideoRecordFileModel temp : requestList) {
            sf.append("<url>");
            sf.append("<resCode>" + temp.getVideoCode() + "</resCode>");
            sf.append("<id>" + temp.getSn() + "</id>");
            sf.append("</url>");
        }
        sf.append("</channelInfoGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/

   /* @Override
    public BaseMsg requestStopRecord4RealTimeVideo(
            ArrayList<VideoRecordFileModel> requestList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='StopRecord4RealTimeVideo'>");
        sf.append("<parameters>");
        sf.append("<channelInfoGroup>");
        for (VideoRecordFileModel temp : requestList) {
            sf.append("<url>");
            sf.append("<resCode>" + temp.getVideoCode() + "</resCode>");
            sf.append("<id>" + temp.getSn() + "</id>");
            sf.append("</url>");
        }
        sf.append("</channelInfoGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }
*/
    @Override
    public String reportCSAlarm4StrProtocol(VAlarm alarm) {
        // 构造返回命令
        StringBuffer sf = new StringBuffer();
        StringBuffer bodysf = new StringBuffer();

        bodysf.append("id=" + alarm.getId() + ",");
        bodysf.append("name=" + alarm.getName() + ",");
        if (alarm.getDescription() == null) {
            bodysf.append("description=,");
        } else {
            bodysf.append("description=" + alarm.getDescription() + ",");
        }

        if (alarm.getAlarmType() == null) {
            bodysf.append("alarmType=,");
        } else {
            bodysf.append("alarmType=" + alarm.getAlarmType() + ",");
        }

        if (alarm.getAlarmlevel() == null) {
            bodysf.append("alarmLevel=,");
        } else {
            bodysf.append("alarmLevel=" + alarm.getAlarmlevel() + ",");
        }

        if (alarm.getBegintime() == null) {
            bodysf.append("beginTime=,");
        } else {
            bodysf.append("beginTime=" + alarm.getBegintime() + ",");
        }

        if (alarm.getEndtime() == null) {
            bodysf.append("endTime=,");
        } else {
            bodysf.append("endTime=" + alarm.getEndtime() + ",");
        }

        if (alarm.getAlarmstate() == null) {
            bodysf.append("alarmState=,");
        } else {
            bodysf.append("alarmState=" + alarm.getAlarmstate() + ",");
        }

        if (alarm.getAffirmman() == null) {
            bodysf.append("affirmMan=,");
        } else {
            bodysf.append("affirmMan=" + alarm.getAffirmman() + ",");
        }

        if (alarm.getAffirm() == null) {
            bodysf.append("afirmTime=,");
        } else {
            bodysf.append("afirmTime=" + alarm.getAffirm() + ",");
        }
        if (alarm.getCleanman() == null) {
            bodysf.append("cleanMan=,");
        } else {
            bodysf.append("cleanMan=" + alarm.getCleanman() + ",");
        }
        if (alarm.getCleantime() == null) {
            bodysf.append("cleanTime=,");
        } else {
            bodysf.append("cleanTime=" + alarm.getCleantime() + ",");
        }
        bodysf.append("isLinkPic=,");
        bodysf.append("^");
        sf.append("ReportAlarm");
        sf.append(";");
        sf.append(bodysf);
        return sf.toString();
    }

    @Override
    public String notifyUserLogout4StrProtocol(String userId) {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("NotifyUserLogout");
        sf.append(";");
        sf.append("0");
        sf.append(";");
        sf.append(userId);
        return sf.toString();
    }

    @Override
    public BaseMsg applyDeviceOnlineState(String serverCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplyDeviceOnlineState'>");
        sf.append("<parameters>");
        sf.append("<id>" + serverCode + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg rebootService(ArrayList<String> serviceCodeList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='RebootService'>");
        sf.append("<parameters>");
        sf.append("<serviceGroup>");
        for (String temp : serviceCodeList) {
            sf.append("<url>");
            sf.append("<id>" + temp + "</id>");
            sf.append("</url>");
        }
        sf.append("</serviceGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public String reportCameraState4StrProtocol(
            ArrayList<AMQDeviceStateModel> list) {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("ReportCameraState");
        sf.append(";");
        for (AMQDeviceStateModel temp : list) {
            sf.append("code=" + temp.getResId());
            sf.append(",");
            sf.append("state=" + temp.getStateI());
            sf.append("^");
        }
        String result = sf.toString();
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String reportDeviceState4StrProtocol(
            ArrayList<AMQDeviceStateModel> list) {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("ReportDeviceState");
        sf.append(";");
        for (AMQDeviceStateModel temp : list) {
            sf.append("code=");
            sf.append(temp.getResId());
            sf.append(",state=");
            sf.append(temp.getStateI());
            sf.append("^");
        }
        String result = sf.toString();
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String forceTheUserLogout() {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("ForceTheUserLogout");
        return sf.toString();

    }

    @Override
    public BaseMsg requestDeleteNVRChannelInfo(
            ArrayList<NVRChannelInfoModel> ipcList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='DelNVRChannelInfo'>");
        sf.append("<parameters>");
        sf.append("<channelInfoGroup>");
        for (NVRChannelInfoModel temp : ipcList) {
            sf.append("<url>");
            sf.append("<resId>" + temp.getChannelCode() + "</resId>");
            sf.append("</url>");
        }
        sf.append("</channelInfoGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg requestUpdateNVRChannelInfo(
            ArrayList<NVRChannelInfoModel> ipcList) {
        StringBuffer sf = new StringBuffer();
        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ModifyNVRChannelInfo'>");
        sf.append("<parameters>");
        sf.append("<channelInfoGroup>");
        for (NVRChannelInfoModel temp : ipcList) {
            sf.append("<url>");
            sf
                    .append("<deviceCode>" + temp.getParentFdCode()
                            + "</deviceCode>");
            sf.append("<ip>" + temp.getIp() + "</ip>");
            sf.append("<port>" + temp.getPort() + "</port>");
            sf.append("<userName>" + temp.getAccount() + "</userName>");
            sf.append("<password>" + temp.getPassword() + "</password>");
            sf.append("<channelNum>" + temp.getChannelNum() + "</channelNum>");
            sf.append("<resId>" + temp.getChannelCode() + "</resId>");
            sf.append("</url>");
        }
        sf.append("</channelInfoGroup>");
        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public BaseMsg applyServiceOnlineState(String serverCode) {
        StringBuffer sf = new StringBuffer();

        // 构造协议
        sf.append(XMLHEADER);
        sf.append("<request command='ApplyServiceOnlineState'>");
        sf.append("<parameters>");
        sf.append("<id>" + serverCode + "</id>");

        sf.append("</parameters>");
        sf.append("</request>");
        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }

    @Override
    public String reportServiceState4StrProtocol(
            ArrayList<AMQDeviceStateModel> list) {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("ReportServiceState");
        sf.append(";");
        for (AMQDeviceStateModel temp : list) {
            sf.append("code=");
            sf.append(temp.getResId());
            sf.append(",state=");
            sf.append(temp.getStateI());
            sf.append("^");
        }
        String result = sf.toString();
        return result.substring(0, result.length() - 1);
    }

    @Override
    public String reportUserState4StrProtocol(String userCode, int state) {
        StringBuffer sf = new StringBuffer();
        // 构造返回命令
        sf.append("ReportUserState");
        sf.append(";");
        sf.append("code=");
        sf.append(userCode);
        sf.append(",state=");
        sf.append(state);
        sf.append("^");
        String result = sf.toString();
        return result.substring(0, result.length() - 1);
    }

   /* @Override
    public BaseMsg changeVideoAlgorithm(int opt,
                                        ArrayList<VideoAlgorithmModel> list) {
        StringBuffer sf = new StringBuffer();
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }

        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {
                // 构造返回命令
                sf.append(XMLHEADER);
                sf.append("<request command='ModifyAlgorithm'>");

                sf.append("<parameters>");
                sf.append("<num>");
                sf.append(list.size());
                sf.append("</num>");
                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");

                sf.append("<AlgorithmGroup>");

                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    VideoAlgorithmModel temp = list.get(i);
                    sf.append("<URL>");

                    sf.append("<configId>");
                    sf.append(temp.getId());
                    sf.append("</configId>");

                    sf.append("<serviceId>");
                    sf.append(temp.getAnalysisServerCode());
                    sf.append("</serviceId>");

                    sf.append("<resId>");
                    sf.append(  temp.getVideoCode() );
                    sf.append("</resId>");

                    sf.append("<name>");
                    if(temp.getVideoCode()!=null){
                        sf.append(temp.getVideoName());
                    }
                    sf.append("</name>");

                    sf.append("<rtspUrl>");
                    sf.append(temp.getRpstUrl());
                    sf.append("</rtspUrl>");

                    sf.append("<resolution>");
                    sf.append(temp.getVideoResolution());
                    sf.append("</resolution>");

                    sf.append("<type>");
                    sf.append(temp.getAlgorithmType());
                    sf.append("</type>");

                    sf.append("<area>");
                    sf.append(temp.getArea());
                    sf.append("</area>");

                    sf.append("<params>");
                    sf.append(temp.getParameter());
                    sf.append("</params>");

                    sf.append("<opt>");
                    sf.append("4");
                    sf.append("</opt>");

                    sf.append("</URL>");

                }
                sf.append("</AlgorithmGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }

        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }
*/

   /* public BaseMsg changeEmployee(String serverCode, int opt, ArrayList<EmployeeModel> list) {
        StringBuffer sf = new StringBuffer();
        int totalPackage = 0;
        int packageSize = 100;
        // 查询服务信息

        totalPackage = list.size() / packageSize;
        if (list.size() % packageSize != 0) {
            totalPackage = totalPackage + 1;
        }

        for (int j = 0; j < totalPackage; j++) {
            // 组包
            {
                // 构造返回命令
                sf.append(XMLHEADER);
                sf.append("<request command='ModifyUserFace'>");

                sf.append("<parameters>");
                sf.append("<num>");
                sf.append(list.size());
                sf.append("</num>");
                sf.append("<totalPkt>");
                sf.append(totalPackage);
                sf.append("</totalPkt>");
                sf.append("<pktNum>");
                sf.append(j);
                sf.append("</pktNum>");
                sf.append("<UserFaceGroup>");
                // 遍历结果集
                for (int i = 0; i < list.size(); i++) {
                    EmployeeModel temp = list.get(i);
                    sf.append("<URL>");

                    sf.append("<opt>");
                    sf.append("4");
                    sf.append("</opt>");

                    sf.append("<userCode>");
                    sf.append(temp.getCode());
                    sf.append("</userCode>");

                    sf.append("<serviceId>");
                    sf.append(serverCode);
                    sf.append("</serviceId>");

                    sf.append("<FaceGroup>");
                    for(String faceUrl:temp.getUserFaceUrlList()){
                        sf.append("<faceUrl>");
                        sf.append(faceUrl);
                        sf.append("</faceUrl>");
                    }
                    sf.append("</FaceGroup>");
                    sf.append("</URL>");
                }
                sf.append("</UserFaceGroup>");
                sf.append("</parameters>");
                sf.append("</request>");
            }
        }

        log.info("发送给CMS数据为：" + sf.toString());
        return ProtocolWrapper.wrap(sf.toString());
    }*/
}

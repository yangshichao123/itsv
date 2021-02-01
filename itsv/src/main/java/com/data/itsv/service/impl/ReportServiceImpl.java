package com.data.itsv.service.impl;

import com.data.itsv.mapper.VEvidenceFileMapper;
import com.data.itsv.mapper.VFdeviceMapper;
import com.data.itsv.mapper.VServerMapper;
import com.data.itsv.model.*;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.model.vo.VVideoVo;
import com.data.itsv.netty.client.BootNettyChannelInboundHandlerAdapter;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.ReportService;
import com.data.itsv.service.VideoService;
import com.data.itsv.util.DateUtils;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.SocketHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Resource
    private VEvidenceFileMapper vEvidenceFileMapper;
    @Resource
    private VFdeviceMapper vFdeviceMapper;
    @Resource
    private ProtocolRequest protocolRequest;
    @Resource
    private VServerMapper serverMapper;
    @Resource
    private VideoService videoService;
    @Override
    public void addStoreFile(ArrayList<VEvidenceFile> list) {

        for (VEvidenceFile vEvidenceFile : list) {

            vEvidenceFileMapper.insertSelective(vEvidenceFile);

        }
    }

    @Override
    public void reportDeviceState(ArrayList<AMQDeviceStateModel> list) {
// 获取协议
        String strProtocol =this.reportDeviceState4StrProtocol(list);
        // 遍历集合发送信息
        Iterator entries = HashMapHelper.getUserConnectionMap().entrySet()
                .iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            ChannelHandlerContext session = (ChannelHandlerContext) entry.getValue();
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, strProtocol);
            }
        }
        // 向设备发起查询设备下通道信息
        for (AMQDeviceStateModel temp : list) {

            if ("1".equals(temp.getStateI())) {
                // 判断设备是否为NVR，如果是NVR设备则发送查询NVR通道信息
                // 查询设备信息
                List<VFdeviceVo> fdList = this.getFD("", "", "", "", temp
                        .getResId(), "", "1", "1");
                if (fdList != null
                        && fdList.size() > 0
                        && "118".equals(fdList.get(0).getFdTemplate().getFdtypeCode()
                        )) {

                    this.getNVRChannelInfo("", temp.getResId());
                }
            }
        }
    }

    private void getNVRChannelInfo(String userCode, String fdCode) {
        BootNettyChannelInboundHandlerAdapter.sendData(protocolRequest.requestGetNVRChannelInfo(fdCode));
        log.info("调用接口getNVRChannelInfo，传入参数为：userCode=" + userCode
                + ",fdCode=" + fdCode + ",结果：result=true");
    }



    private List<VFdeviceVo> getFD(String pUserId, String fdTemplateCode,
                                 String longitude, String latitude, String code, String serverCode,
                                 String pageSize, String pageIndex) {

        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageInfo<VFdeviceVo> pageInfo = new PageInfo<>(vFdeviceMapper.getFD(pUserId,fdTemplateCode,longitude,latitude,code,serverCode,"",""));
        List<VFdeviceVo> list = pageInfo.getList();
        for (VFdeviceVo vFdeviceVo : list) {
            vFdeviceVo.setTotalNumI(pageInfo.getSize());
            vFdeviceVo.setTotalPageNumI(pageInfo.getPages());
            vFdeviceVo.setRn(pageInfo.getSize());
        }
        return  list;
    }

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
    public void reportServiceState(ArrayList<AMQDeviceStateModel> list) {
        // 获取协议
        String strProtocol = protocolRequest.reportServiceState4StrProtocol(list);
        // 遍历集合发送信息
        Iterator entries = HashMapHelper.getUserConnectionMap().entrySet()
                .iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            ChannelHandlerContext session = (ChannelHandlerContext) entry.getValue();
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, strProtocol);
            }
        }
        for (AMQDeviceStateModel temp : list) {
            // 更新数据库状态信息
            this.updateServiceState(temp.getResId(),"","", temp.getStateI(), temp
                    .getTime(),"");
        }
    }

    private void updateServiceState(String code, String ip,
                                    String port, String state, String time, String sessionId) {

        Example example = new Example(VServer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        VServer vServer=new VServer();
        if(state!=null && !"".equals(state)){

            vServer.setOnlineState(Integer.parseInt(state));
        }
        if(time!=null && !"".equals(time)){

            vServer.setOnlineTime(DateUtils.parseDate(time,"yyyy-MM-dd HH:mm:ss"));
        }
        if(ip!=null && !"".equals(ip)){
            vServer.setIp(ip);
        }
        if(port!=null && !"".equals(port)){
            vServer.setAccPort(Integer.parseInt(port));
        }
        if(sessionId!=null && !"".equals(sessionId)){
            vServer.setSessionId(sessionId);
        }
        serverMapper.updateByExampleSelective(vServer,example);
    }

    @Override
    public boolean reportNVRChannelInfo(String fdCode, String videoCode, String userName, String password, String ip, String port) {

        boolean result = false;

        result = true;
        // 查找设备下通道是否存在，如果不存在进行添加通道
        String addVideoCode = null;
        if (videoCode == null || videoCode.trim().length() == 0) {
            addVideoCode = videoService.addVideoIn(ip, fdCode, "0", "", "", "");
            if (addVideoCode == null || addVideoCode.trim().length() == 0) {
                return false;
            }
        } else {
            addVideoCode = videoCode;
        }
        // 更新设备子设备关系
        // 查询通道信息
        PageInfo<VVideoVo> pageInfo = videoService.getVideo("", addVideoCode, "",
                "", fdCode, "1", "1");
        List<VVideoVo> listVideo= pageInfo.getList();
        if (listVideo != null && listVideo.size() > 0) {
            result = videoService.mergeMasterSlaveFd("", fdCode, addVideoCode,
                    listVideo.get(0).getVchannel()+"", userName, password, ip,
                    port);
        }
        return result;

    }


    @Override
    public boolean reportGetCameraOSDResult(
            ArrayList<CameraOSDInfoModel> cameraOSDList) {
        if (cameraOSDList != null && cameraOSDList.size() != 0) {
            // 1、 摄像机OSD信息入库
            for (CameraOSDInfoModel temp : cameraOSDList) {
                this.resetVideoOSD(temp);
            }
        }
        return true;
    }

    @Override
    public boolean resetVideoOSD(CameraOSDInfoModel osd) {
        // 1、更新nameOSD和timeOSD信息
        boolean updateResult = videoService.updateVideo2CMS(null, osd.getVideoCode(), null, osd
                        .getNameOSD().getContent(), null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, osd
                        .getNameOSD().getContent(), null, osd.getNameOSD()
                        .getEnableHide()
                        + "", osd.getNameOSD().getX() + "", osd.getNameOSD()
                        .getY()
                        + "", osd.getTimeOSD().getEnableHide() + "", osd
                        .getTimeOSD().getX()
                        + "", osd.getTimeOSD().getY() + "", null, null, null, null, null,
                null, null, null, null, "0");
        // 2、更新追加信息
        // 2.1删除原有追加OSD信息
        boolean deleteResult = videoService.deleteVideoAllAppendOSD(null, osd
                .getVideoCode());
        // 2.2添加新OSD信息
        ArrayList<OSDModel> osdList = osd.getAppendOSDList();
        for (OSDModel tempOSD : osdList) {
            boolean addResult = videoService
                    .addVideoAppendOSD(null, osd.getVideoCode(), tempOSD
                            .getContent(), tempOSD.getX() + "", tempOSD.getY()
                            + "", tempOSD.getId()+"", tempOSD.getEnableHide() + "");
        }
        return true;
    }

    @Override
    public boolean reportGetFDOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList) {
        return false;
    }
}

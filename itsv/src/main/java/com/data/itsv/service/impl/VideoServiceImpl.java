package com.data.itsv.service.impl;

import com.data.itsv.mapper.VMasterSlaveFdMapper;
import com.data.itsv.mapper.VVideoAppendOsdMapper;
import com.data.itsv.mapper.VVideoMapper;
import com.data.itsv.mapper.VVssVideoMapper;
import com.data.itsv.model.*;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.model.vo.VMasterSlaveFdVo;
import com.data.itsv.model.vo.VVideoVo;
import com.data.itsv.netty.ProtocolRequestProcesser;
import com.data.itsv.netty.client.BootNettyChannelInboundHandlerAdapter;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.VideoService;
import com.data.itsv.util.DateUtils;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.SocketHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@Service("VideoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private ProtocolRequest protocolRequest;
    @Autowired
    private ProtocolRequestProcesser protocolRequestProcesser;
    @Autowired
    private VVideoMapper videoMapper;
    @Autowired
    private VVssVideoMapper vssVideoMapper;
    @Autowired
    private VVideoAppendOsdMapper videoAppendOsdMapper;
    @Autowired
    private VMasterSlaveFdMapper vMasterSlaveFdMapper;
    @Autowired

    @Override
    public void reportCameraState(ArrayList<AMQDeviceStateModel> list) {
        // TODO Auto-generated method stub
        /*********************** old send camera state *****************************/
        AMQMessageModel amq = new AMQMessageModel();
        amq.setType("cameraState");
        amq.setList(list);
        // 发送订阅的信息
        protocolRequestProcesser.productMessage(amq);
        /*********************** old send camera state *****************************/
        /*********************** new send camera state *****************************/
        // new 发送客户端协议
        // 获取协议
        String strProtocol = protocolRequest.reportCameraState4StrProtocol(list);
        // 遍历集合发送信息
        Iterator entries = HashMapHelper.getUserConnectionMap().entrySet()
                .iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            ChannelHandlerContext session = (ChannelHandlerContext) entry.getValue();
            if (session != null) {
                SocketHelper.sendUserClientMsg(session, strProtocol);
            }
        }
        strProtocol = null;
        /*********************** new send camera state *****************************/
        /*********************** 查询摄像机OSD信息 *****************************/
        // 向设备发起查询OSD设置信息
        for (AMQDeviceStateModel videoState : list) {
            // 查询摄像机信息
            List<VVideo> videoList = this.getVideoByCode(videoState
                    .getResId());
            for (VVideo video : videoList) {
                if ("1".equals(videoState.getStateI())) {
                    this.getCameraOSD("", video.getFdCode(), video.getCode(),
                            video.getVchannel()+"");
                }
            }
        }
        /*********************** 查询摄像机OSD信息 *****************************/
    }

    @Override
    public List<VVideo> getVideoByCode(String code) {
        VVideo vVideo=new VVideo();
        vVideo.setCode(code);
        List<VVideo> select = videoMapper.select(vVideo);
        return select;
    }
   

    @Override
    public boolean getCameraOSD(String userCode, String fdCode,
                                String videoCode, String channelNum) {
        BootNettyChannelInboundHandlerAdapter.ctx.write(protocolRequest.requestGetCameraOSD(fdCode, videoCode,
                channelNum));
        log.info("调用接口getCameraOSD，传入参数为：userCode=" + userCode + ",fdCode="
                + fdCode + ",videoCode=" + videoCode + ",channelNum="
                + channelNum + ",结果：result=true");
        return true;
    }

    @Override
    public String getVideoByUidDirId(String useId, String catologId, String s) {
        StringBuffer sb=new StringBuffer();
        List<VVideoVo> vVideoVos=videoMapper.getVideoByUidDirId(useId,catologId,s);
        for (VVideoVo vVideoVo : vVideoVos) {
            //暂时没有音频
           // String  voiceId=  this.getVoiceChannelByVideoId(useId,vVideoVo.getCode());
            String  voiceId=  "";
            sb.append("<Node type='video' state='" + vVideoVo.getState()
                    + "' getPreFlag='false' name='" + vVideoVo.getName() + "' ptzFlag='"
                    + vVideoVo.getPtz() + "' voiceId='" + voiceId + "' code='" + vVideoVo.getCode()
                    + "' realCodeType='" + vVideoVo.getRealcodetype() + "'>");

            sb.append("</Node>");
        }
        return sb.toString();
    }

    @Override
    public String getVideoByIGoupId(String useId, String catologId) {

        return null;
    }

    @Override
    public String getVideoByTourId(String useId, String tourId) {

        StringBuffer sb=new StringBuffer();
        List<VVideoVo> vVideoVos=videoMapper.getVideoByTourId(useId,tourId);
        for (VVideoVo vVideoVo : vVideoVos) {
            //暂时没有音频
            // String  voiceId=  this.getVoiceChannelByVideoId(useId,vVideoVo.getCode());
            String  voiceId=  "";
            sb.append("<Node type='video' state='" + vVideoVo.getState()
                    + "' getPreFlag='false' name='" + vVideoVo.getName() + "' ptzFlag='"
                    + vVideoVo.getPtz() + "' voiceId='" + voiceId + "' code='" + vVideoVo.getCode()
                    + "' realCodeType='" + vVideoVo.getRealcodetype() + "'>");

            sb.append("</Node>");
        }
        return sb.toString();
    }
    @Override
    public String addVideoIn(String name, String fdCode, String channelNum,
                             String desc, String bLocal, String reserve) {


        if(channelNum==null && "".equals(channelNum)){
            VVideo vVideo=new VVideo();
            vVideo.setFdCode(fdCode);
            vVideo= videoMapper.selectChannelNum(vVideo);
            channelNum=vVideo.getVchannel()+"";
        }
        VVideo vVideo=new VVideo();
        vVideo.setFdCode(fdCode);
        vVideo.setName(name);
        vVideo.setDescs(desc);
        vVideo.setVchannel(Integer.parseInt(channelNum));
        vVideo.setBlocal(Integer.parseInt(bLocal));
        vVideo.setReserve(reserve);
        String code=fdCode.substring(0,10)+"1315"+ (DateUtils.formatDate(new Date(),"HHmmss"));
        vVideo.setCode(code);
        int i = videoMapper.insertSelective(vVideo);
        if(i>0){
            // 通知CMS添加視頻通道
            PageInfo<VVideoVo> pageInfo = this.getVideo("", code, "",
                    "", fdCode, "1", "1");
            ArrayList<VVideoVo> list = (ArrayList<VVideoVo>) pageInfo.getList();
            if (list.size() > 0) {
                this.noticeChangeCamResource("1", list);
            }
            return code;
        }else {
            return "";
        }

    }
    @Override
    public PageInfo<VVideoVo> getVideo(String userId, String code,
                                         String name, String type, String fdCode, String pageSize,
                                         String pageIndex) {
        VVideo vVideo=new VVideo();
        vVideo.setName(name);
        vVideo.setCode(code);
        vVideo.setCameraTypeCode(Integer.parseInt(type));
        vVideo.setFdCode(fdCode);

        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageInfo<VVideoVo> pageInfo = new PageInfo<>(videoMapper.getVideo(vVideo));

        return  pageInfo;
    }

    @Override
    public void noticeChangeCamResource(String opt, ArrayList<VVideoVo> list) {
        // 数据打包 操作类型，1增加2删除3修改
        BaseMsg bm = protocolRequest.changeCamResource(opt, list);
        // 发送到CMS
        try {
            BootNettyChannelInboundHandlerAdapter.ctx.write(bm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean mergeMasterSlaveFd(String userId, String fdCode, String slaveFdChannelCode, String channelNum, String slaveFDAccount, String slaveFDPassword, String slaveFDIP, String slaveFDPort) {
        boolean result = false;
        if ((slaveFDPort == null || slaveFDPort.trim().length() == 0)
                && (slaveFDAccount == null || slaveFDAccount.trim().length() == 0)
                && (slaveFDPassword == null || slaveFDPassword.trim().length() == 0)
                && (slaveFDIP == null || slaveFDIP.trim().length() == 0)) {
            // 删除关联关系
            result = this.deleteMasterSlaveFd(userId, slaveFdChannelCode);
        } else {
            // 查询是否已经子设备
            ArrayList<VMasterSlaveFd> fdList = this.getMasterSlaveFd(userId,
                    slaveFdChannelCode);
            if (fdList != null && fdList.size() > 0) {
                result = this.updateMasterSlaveFd(userId, fdCode, channelNum,
                        slaveFdChannelCode, slaveFDAccount, slaveFDPassword,
                        slaveFDIP, slaveFDPort);
            } else {
                result = this.addMasterSlaveFd(userId, fdCode,
                        slaveFdChannelCode, channelNum, slaveFDAccount,
                        slaveFDPassword, slaveFDIP, slaveFDPort);
            }
        }

        return result;
    }




    @Override
    public boolean deleteMasterSlaveFd(String userId, String slaveFdChannelCode) {
        boolean result=false;
// 查询出子设备信息
        ArrayList<VMasterSlaveFd> childFDList = this.getMasterSlaveFd(userId,
                slaveFdChannelCode);
        if (childFDList != null && childFDList.size() > 0) {
            Example example = new Example(VMasterSlaveFd.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("masterFdChannelCode", slaveFdChannelCode);
            int i = vMasterSlaveFdMapper.deleteByExample(example);
            if(i>0)
                result=true;
            log.info("调用接口deleteMasterSlaveFd,传入参数：" + "userId=" + userId
                    + ",slaveFdChannelCode=" + slaveFdChannelCode + ",结果："
                    + result);
            // 发送删除命令到设备
            if (result) {
                PageInfo<VVideoVo> video = this.getVideo("",
                        slaveFdChannelCode, "", "", "", "1", "1");
                List<VVideoVo> videoList = video.getList();
                if (videoList != null && videoList.size() > 0) {
                    ArrayList<NVRChannelInfoModel> ipcList = new ArrayList<>();
                    NVRChannelInfoModel temp = new NVRChannelInfoModel();
                    temp.setParentFdCode(videoList.get(0).getFdCode());
                    temp.setChannelCode(videoList.get(0).getCode());
                    temp.setChannelNum(videoList.get(0)
                            .getVchannel());
                    temp.setAccount(childFDList.get(0).getMasterFdAccount());
                    temp.setPassword(childFDList.get(0).getMasterFdPassword());
                    temp.setIp(childFDList.get(0).getMasterFdIp());
                    temp.setPort(childFDList.get(0).getMasterFdProt());
                    ipcList.add(temp);

                    this.deleteNVRChannelInfo(userId, ipcList);
                }

            }
        }

        return result;
    }

    public void deleteNVRChannelInfo(String userId, ArrayList<NVRChannelInfoModel> ipcList) {
        BootNettyChannelInboundHandlerAdapter.ctx.write(protocolRequest.requestDeleteNVRChannelInfo(ipcList));
        log.info("调用接口deleteNVRChannelInfo，传入参数为：userCode=" + userId
                + ",ipcList=" + ipcList.size() + ",结果：result=true");
    }

    public ArrayList<VMasterSlaveFd> getMasterSlaveFd(String userId, String slaveFdChannelCode) {
        VMasterSlaveFd vMasterSlaveFd=new VMasterSlaveFdVo();
        vMasterSlaveFd.setMasterFdChannelCode(slaveFdChannelCode);

        List<VMasterSlaveFd> select = vMasterSlaveFdMapper.select(vMasterSlaveFd);
        ArrayList<VMasterSlaveFd> arrayList=new ArrayList<>();
        arrayList.addAll(select);

        log.info("调用接口getMasterSlaveFd,传入参数：" + "userId=" + userId
                + "slaveFdChannelCode=" + slaveFdChannelCode + ",结果："
                + select.size());
        return arrayList;
    }

    @Override
    public boolean updateMasterSlaveFd(String userId, String fdCode, String channelNum,
                                       String slaveFdChannelCode, String slaveFDAccount,
                                       String slaveFDPassword, String slaveFDIP, String slaveFDPort) {
        Example example = new Example(VMasterSlaveFd.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("masterFdChannelCode", slaveFdChannelCode);
        VMasterSlaveFd vMasterSlaveFd=new VMasterSlaveFd();
        vMasterSlaveFd.setMasterFdAccount(slaveFDAccount);
        vMasterSlaveFd.setMasterFdIp(slaveFDIP);
        vMasterSlaveFd.setMasterFdProt(Integer.parseInt(slaveFDPort));
        vMasterSlaveFd.setMasterFdPassword(slaveFDPassword);
        int i = vMasterSlaveFdMapper.updateByExampleSelective(vMasterSlaveFd, example);
        return i>0?true:false;
    }
    @Override
    public boolean addMasterSlaveFd(String userId, String fdCode, String slaveFdChannelCode, String channelNum, String slaveFDAccount, String slaveFDPassword, String slaveFDIP, String slaveFDPort) {
        VMasterSlaveFd vMasterSlaveFd=new VMasterSlaveFd();
        vMasterSlaveFd.setMasterFdChannelCode(slaveFdChannelCode);
        vMasterSlaveFd.setMasterFdAccount(slaveFDAccount);
        vMasterSlaveFd.setMasterFdIp(slaveFDIP);
        vMasterSlaveFd.setMasterFdProt(Integer.parseInt(slaveFDPort));
        vMasterSlaveFd.setMasterFdPassword(slaveFDPassword);
        int i = vMasterSlaveFdMapper.insertSelective(vMasterSlaveFd);
        return i>0?true:false;
    }

    @Override
    public boolean updateVideo(String userId, String code, String cameraTypeCode, String name, String address,
                               String longitude, String latitude, String descs, String fdCode, String codeFormat,
                               String analysistype, String realRes, String realCodeType, String realImageQuality,
                               String realCodeModel, String realTransModel, String recRes, String recCodeType,
                               String recImageQuality, String recCodeModel, String recTransModel, String target,
                               String vChannel, String aChannel, String ptz, String ptzProtocol, String osd,
                               String bLocal, String showOsd, String osdX, String osdY, String showTime,
                               String timeX, String timeY, String showRealStatus, String realStatusX,
                               String realStatusY, String showRecStatus, String recStatusX, String recStatusY,
                               String showAnalysisStatus, String analysisStatusX, String analysisStatusY,
                               String vssCode) {
        VVideo vVideo=new VVideo();
        vVideo.setCode(code);
        vVideo.setCameraTypeCode(Integer.parseInt(cameraTypeCode));
        vVideo.setName(name);
        vVideo.setAddress(address);
        vVideo.setLongitude(Integer.parseInt(longitude));
        vVideo.setLatitude(Integer.parseInt(latitude));
        vVideo.setDescs(descs);
        vVideo.setFdCode(fdCode);
        vVideo.setCodeformat(Integer.parseInt(codeFormat));
        vVideo.setAnalysistype(Integer.parseInt(analysistype));
        vVideo.setRealres(Integer.parseInt(realRes));
        vVideo.setRealcodetype(Integer.parseInt(realCodeType));
        vVideo.setRealimagequality(Integer.parseInt(realImageQuality));
        vVideo.setRealcodemodle(Integer.parseInt(realCodeModel));
        vVideo.setRealtransmodle(Integer.parseInt(realTransModel));
        vVideo.setRecres(Integer.parseInt(recRes));
        vVideo.setReccodetype(Integer.parseInt(recCodeType));
        vVideo.setRecimagequality(Integer.parseInt(recImageQuality));
        vVideo.setReccodemodle(Integer.parseInt(recCodeModel));
        vVideo.setRectransmodle(Integer.parseInt(recTransModel));
        vVideo.setTarget(target);
        vVideo.setVchannel(Integer.parseInt(vChannel));
        vVideo.setAchannel(Integer.parseInt(aChannel));
        vVideo.setPtz(Integer.parseInt(ptz));
        vVideo.setPtzprotocol(Integer.parseInt(ptzProtocol));
        vVideo.setOsd(osd);
        vVideo.setBlocal(Integer.parseInt(bLocal));
        vVideo.setShowosd(Integer.parseInt(showOsd));
        vVideo.setOsdx(Integer.parseInt(osdX));
        vVideo.setOsdy(Integer.parseInt(osdY));
        vVideo.setShowtime(Integer.parseInt(showTime));
        vVideo.setTimex(Integer.parseInt(timeX));
        vVideo.setTimey(Integer.parseInt(timeY));
        vVideo.setShowrealstatus(Integer.parseInt(showRealStatus));
        vVideo.setRealstatusx(Integer.parseInt(realStatusX));
        vVideo.setRealstatusy(Integer.parseInt(realStatusY));
        vVideo.setShowrecstatus(Integer.parseInt(showRecStatus));
        vVideo.setRecstatusx(Integer.parseInt(recStatusX));
        vVideo.setRecstatusy(Integer.parseInt(recStatusY));
        vVideo.setShowanalysisstatus(Integer.parseInt(showAnalysisStatus));
        vVideo.setAnalysisstatusx(Integer.parseInt(analysisStatusX));
        vVideo.setAnalysisstatusy(Integer.parseInt(analysisStatusY));
        Example example = new Example(VVideo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        int i = videoMapper.updateByExampleSelective(vVideo, example);
        if("0".equals(vssCode)&&i>0){
            return  true;
        }else if(i>0){
            VVssVideo vVssVideo=new VVssVideo();
            vVssVideo.setVideoCode(code);
            vVssVideo.setVssCode(vssCode);
            List<VVssVideo> select = vssVideoMapper.select(vVssVideo);
            if(select.size()<1){
                int insert = vssVideoMapper.insert(vVssVideo);
                if(insert>0)
                    return true;
            }else{
                return  true;
            }

        }
        return false;
    }

    @Override
    public boolean deleteVideoAllAppendOSD(String userCode, String videoCode) {
        Example example = new Example(VVideoAppendOsd.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("videoCode", videoCode);
        int i = videoAppendOsdMapper.deleteByExample(example);
        return i>0?true:false;
    }

    @Override
    public boolean addVideoAppendOSD(String userCode, String videoCode,
                                     String content, String x, String y, String pos, String enableHide) {
        VVideoAppendOsd vVideoAppendOsd=new VVideoAppendOsd();
        vVideoAppendOsd.setContent(content);
        vVideoAppendOsd.setEnableHide(Integer.parseInt(enableHide));
        vVideoAppendOsd.setPos(Integer.parseInt(pos));
        vVideoAppendOsd.setX(Integer.parseInt(x));
        vVideoAppendOsd.setY(Integer.parseInt(y));
        vVideoAppendOsd.setVideoCode(videoCode);

        int i = videoAppendOsdMapper.insertSelective(vVideoAppendOsd);
        return i>0?true:false;
    }



    @Override
    public boolean updateVideo2CMS(String userId, String code, String cameraTypeCode, String name, String address, String longitude, String latitude, String descs, String fdCode, String codeFormat, String analysistype, String realRes, String realCodeType, String realImageQuality, String realCodeModel, String realTransModel, String recRes, String recCodeType, String recImageQuality, String recCodeModel, String recTransModel, String target, String vChannel, String aChannel, String ptz, String ptzProtocol, String osd, String bLocal, String showOsd, String osdX, String osdY, String showTime, String timeX, String timeY, String showRealStatus, String realStatusX, String realStatusY, String showRecStatus, String recStatusX, String recStatusY, String showAnalysisStatus, String analysisStatusX, String analysisStatusY, String vssCode) {
        boolean b = this.updateVideo(userId, code,
                cameraTypeCode, name, address,
                longitude, latitude, descs, fdCode,
                codeFormat, analysistype, realRes,
                realCodeType, realImageQuality, realCodeModel,
                realTransModel, recRes, recCodeType,
                recImageQuality, recCodeModel, recTransModel,
                target, vChannel, aChannel, ptz,
                ptzProtocol, osd, bLocal, showOsd,
                osdX, osdY, showTime, timeX,
                timeY, showRealStatus, realStatusX,
                realStatusY, showRecStatus, recStatusX,
                recStatusY, showAnalysisStatus,
                analysisStatusX, analysisStatusY, vssCode);
        if(b){
            // 发送到CMS//1增加2删除3修改

            PageInfo<VVideoVo> video = this.getVideo("", code, "", "", "", "1", "100");
            List<VVideoVo> list = video.getList();
            ArrayList<VVideoVo> arrayList=new ArrayList<>();
            arrayList.addAll(list);
            if (arrayList.size() > 0) {
                this.noticeChangeCamResource("3", arrayList);
            }
            // 更新坐标
           /* {
                if (longitude.trim().length() > 0
                        && latitude.trim().length() > 0) {
                    MapDAO md = new MapImp();
                    md.updateOrAddResCoordiate(userId, code, "3", longitude
                            .trim(), latitude.trim());
                }

            }*/
        }
        return false;
    }
}
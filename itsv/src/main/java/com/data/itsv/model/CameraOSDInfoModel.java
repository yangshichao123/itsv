package com.data.itsv.model;


import java.util.ArrayList;

/**
 * 摄像机OSD信息
 * @author gaohe
 * */
public class CameraOSDInfoModel extends BaseModel{

    /**
     * 摄像机编号
     * @author gaohe
     * */
    private String videoCode;
    /**
     * 摄像机说在设备编号
     * @author gaohe
     * */
    private String fdCode;
    /**
     * 摄像机所在设备通道号
     * @author gaohe
     * */
    private String channelNum;
    /**
     * 时间OSD信息
     * @author gaohe
     * */
    private OSDModel timeOSD;
    /**
     * 名称OSD信息
     * @author gaohe
     * */
    private OSDModel nameOSD;
    /**
     * 追加OSD
     * @author gaohe
     * */
    private ArrayList<OSDModel> appendOSDList;
    public OSDModel getTimeOSD() {
        return timeOSD;
    }
    public void setTimeOSD(OSDModel timeOSD) {
        this.timeOSD = timeOSD;
    }
    public OSDModel getNameOSD() {
        return nameOSD;
    }
    public void setNameOSD(OSDModel nameOSD) {
        this.nameOSD = nameOSD;
    }
    public ArrayList<OSDModel> getAppendOSDList() {
        return appendOSDList;
    }
    public void setAppendOSDList(ArrayList<OSDModel> appendOSDList) {
        this.appendOSDList = appendOSDList;
    }
    public String getVideoCode() {
        return videoCode;
    }
    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }
    public String getFdCode() {
        return fdCode;
    }
    public void setFdCode(String fdCode) {
        this.fdCode = fdCode;
    }
    public String getChannelNum() {
        return channelNum;
    }
    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }

}

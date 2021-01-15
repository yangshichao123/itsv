package com.data.itsv.service;

import com.data.itsv.model.AMQDeviceStateModel;
import com.data.itsv.model.CameraOSDInfoModel;
import com.data.itsv.model.VEvidenceFile;

import java.util.ArrayList;

public interface ReportService {
    void addStoreFile(ArrayList<VEvidenceFile> list);


    void reportDeviceState(ArrayList<AMQDeviceStateModel> list);

    void reportServiceState(ArrayList<AMQDeviceStateModel> list);

    boolean reportNVRChannelInfo(String deviceCode, String resId, String userName, String password, String ip, String port);

    boolean reportGetFDOSDResult(ArrayList<CameraOSDInfoModel> cameraOSDList);

    boolean reportGetCameraOSDResult(
            ArrayList<CameraOSDInfoModel> cameraOSDList);

    boolean resetVideoOSD(CameraOSDInfoModel osd);
}

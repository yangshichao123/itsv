package com.data.itsv.model.vo;

import com.data.itsv.model.VAlarmSet;
import lombok.Data;

@Data
public class VAlarmSetVo extends VAlarmSet {

    //账号
    private String account;
    //密码
    private String password;
    //设备ip
    private String fdIp;
    //设备port
    private String fdPort;
    //告警类型
    private String alarmTypes;
    //布防开始时间
    private String startTime;
    //布防结束时间
    private String endTime;
    //设备id
    private  String deviceid;
    //设备id
    private  String pagcode;
    //设备id
    private  String auCode;
}

package com.data.itsv.service.impl;

import com.data.itsv.mapper.*;
import com.data.itsv.model.BaseModel;
import com.data.itsv.model.SUser;
import com.data.itsv.model.VAlarm;
import com.data.itsv.model.VAlarmType;
import com.data.itsv.model.vo.VAlarmEvidenceVo;
import com.data.itsv.model.vo.VAlarmSetVo;
import com.data.itsv.model.vo.VAlarmVo;
import com.data.itsv.model.vo.VIlayoutVo;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.UserService;
import com.data.itsv.service.VAlarmSetService;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.SocketHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("VAlarmSetService")
public class VAlarmSetServiceImpl implements VAlarmSetService {
    @Autowired
    private VAlarmSetMapper vAlarmSetMapper;
    @Autowired
    private VAlarmMapper vAlarmMapper;
    @Autowired
    private VAlarmTypeMapper vAlarmTypeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProtocolRequest protocolRequest;
    @Autowired
    private VAlarmEvidenceMapper vAlarmEvidenceMapper;
    @Override
    public ArrayList<VAlarmSetVo> getAlarmResByUserCode(String code) {
      ArrayList<VAlarmSetVo> arrayList=  vAlarmSetMapper.getAlarmResByUserCode(code);
        return arrayList;
    }

    @Override
    public void reportAlarm(ArrayList alarmList) {
        //入库
        for (int i = 0; i < alarmList.size(); i++) {
            VAlarm alarm = (VAlarm) alarmList.get(i);
            //入库
            this.addAlarm(alarm);
        }

        // TODO Auto-generated method stub
        for (int i = 0; i < alarmList.size(); i++) {
            VAlarm alarm = (VAlarm) alarmList.get(i);

            // 1、查询订阅该告警信息客户端用户,如果用户在线则进行告警信息推送
            List<SUser> userList = userService.getUsersByAlarmResId(alarm
                    .getId()
                    + alarm.getAlarmType());
            for (SUser clientUser : userList) {
                ChannelHandlerContext channelHandlerContext = HashMapHelper.getUserConnectionMap()
                        .get(clientUser.getCode());
                if (channelHandlerContext != null ) {
                    // 发送告警信息
                    this.reportCSAlarm4StrProtocol(channelHandlerContext, alarm);
                }
            }

        }
    }

    @Override
    public void reportCSAlarm4StrProtocol(ChannelHandlerContext session,
                                          VAlarm alarm) {

        // 发送请求方
        if (session != null ) {
            SocketHelper.sendUserClientMsg(session, protocolRequest.reportCSAlarm4StrProtocol(alarm));
        }
    }

    @Override
    public List<VAlarmType> getAlarmType() {
        List<VAlarmType> vAlarmTypes =vAlarmTypeMapper.selectAll();
        return vAlarmTypes;
    }

    @Override
    public void addAlarm(VAlarm alarm) {
        vAlarmMapper.insertSelective(alarm);
    }

    @Override
    public PageInfo<VAlarmVo> getAlarmInfo(String userId, String alarmId, String alarmType, String alarmState, String content, String pagesize, String pageindex) {
        VAlarmVo vAlarmVo=new VAlarmVo();
        vAlarmVo.setAlarmid(alarmId);
        vAlarmVo.setAlarmType(alarmType);
        vAlarmVo.setAlarmstate(Integer.parseInt(alarmState));
        PageHelper.startPage(Integer.parseInt(pageindex), Integer.parseInt(pagesize));
        PageInfo<VAlarmVo> pageInfo = new PageInfo<>(vAlarmMapper.selectValarmVo(vAlarmVo));
        return pageInfo;
    }

    @Override
    public List<VAlarmEvidenceVo> getAlarmEvidence(String userId, String alarmId) {
        List<VAlarmEvidenceVo> vAlarmEvidenceVos=  vAlarmEvidenceMapper.getAlarmEvidence(alarmId);
        return vAlarmEvidenceVos;
    }
}

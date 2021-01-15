package com.data.itsv.service;

import com.data.itsv.model.VAlarm;
import com.data.itsv.model.VAlarmType;
import com.data.itsv.model.vo.VAlarmEvidenceVo;
import com.data.itsv.model.vo.VAlarmSetVo;
import com.data.itsv.model.vo.VAlarmVo;
import com.github.pagehelper.PageInfo;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 告警资源
 */
public interface VAlarmSetService {
    /**
     * 通过用户code查询订阅的告警资源
     * @param code
     * @return
     */
    ArrayList<VAlarmSetVo> getAlarmResByUserCode(String code);

    void reportAlarm(ArrayList<Object> list);
    void addAlarm(VAlarm alarm);
    List<VAlarmType> getAlarmType();

    public void reportCSAlarm4StrProtocol(ChannelHandlerContext session,
                                          VAlarm alarm);


    PageInfo<VAlarmVo> getAlarmInfo(String userId, String s, String alarmType, String alarmState, String s1, String pagesize, String pageindex);

    /**
     *
     * @param userId
     * @param alarmId
     * @return
     */
    List<VAlarmEvidenceVo> getAlarmEvidence(String userId, String alarmId);
}

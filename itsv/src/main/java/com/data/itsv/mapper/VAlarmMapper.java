package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarm;
import com.data.itsv.model.vo.VAlarmVo;

import java.util.List;

public interface VAlarmMapper extends BaseMapper<VAlarm> {
    int insert(VAlarm record);

    int insertSelective(VAlarm record);

    VAlarm selectByPrimaryId(String alarmid);

    List<VAlarmVo> selectValarmVo(VAlarmVo vAlarmVo);
}
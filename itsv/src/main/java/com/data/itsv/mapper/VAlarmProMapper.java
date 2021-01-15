package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmPro;

public interface VAlarmProMapper extends BaseMapper<VAlarmPro> {
    int insert(VAlarmPro record);

    int insertSelective(VAlarmPro record);

    VAlarmPro selectByPrimaryId(String alarmid);
}
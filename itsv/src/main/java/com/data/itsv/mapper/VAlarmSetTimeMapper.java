package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmSetTime;

public interface VAlarmSetTimeMapper extends BaseMapper<VAlarmSetTime> {
    int insert(VAlarmSetTime record);

    int insertSelective(VAlarmSetTime record);
}
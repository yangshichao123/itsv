package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmSetTime;
import com.data.itsv.model.VAlarmSetVideolink;

public interface VAlarmSetVideolinkMapper extends BaseMapper<VAlarmSetVideolink> {
    int insert(VAlarmSetVideolink record);

    int insertSelective(VAlarmSetVideolink record);
}
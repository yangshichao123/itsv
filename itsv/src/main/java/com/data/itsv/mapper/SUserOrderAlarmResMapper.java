package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SUserOrderAlarmRes;

public interface SUserOrderAlarmResMapper extends BaseMapper<SUserOrderAlarmRes> {
    int insert(SUserOrderAlarmRes record);

    int insertSelective(SUserOrderAlarmRes record);
}
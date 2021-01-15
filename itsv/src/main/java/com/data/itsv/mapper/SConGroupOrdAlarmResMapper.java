package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SConGroupOrdAlarmRes;

public interface SConGroupOrdAlarmResMapper extends BaseMapper<SConGroupOrdAlarmRes> {
    int insert(SConGroupOrdAlarmRes record);

    int insertSelective(SConGroupOrdAlarmRes record);

    SConGroupOrdAlarmRes selectByPrimaryId(Integer id);
}
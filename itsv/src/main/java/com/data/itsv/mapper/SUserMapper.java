package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SUser;

import java.util.ArrayList;
import java.util.List;

public interface SUserMapper extends BaseMapper<SUser> {
    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryId(String code);

    SUser selectByAP(SUser sUser);

    List<SUser> getUsersByAlarmResId(String alarmResId);
}
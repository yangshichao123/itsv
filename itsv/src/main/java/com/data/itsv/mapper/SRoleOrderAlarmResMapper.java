package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRoleOrderAlarmRes;

public interface SRoleOrderAlarmResMapper extends BaseMapper<SRoleOrderAlarmRes> {
    int insert(SRoleOrderAlarmRes record);

    int insertSelective(SRoleOrderAlarmRes record);
}
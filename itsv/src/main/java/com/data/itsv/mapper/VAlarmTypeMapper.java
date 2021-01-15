package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmType;

public interface VAlarmTypeMapper extends BaseMapper<VAlarmType> {
    int insert(VAlarmType record);

    int insertSelective(VAlarmType record);

    VAlarmType selectByPrimaryId(Integer id);
}
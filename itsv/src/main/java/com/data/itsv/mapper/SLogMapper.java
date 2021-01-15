package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SLog;

public interface SLogMapper extends BaseMapper<SLog> {
    int insert(SLog record);

    int insertSelective(SLog record);

    SLog selectByPrimaryId(Integer id);
}
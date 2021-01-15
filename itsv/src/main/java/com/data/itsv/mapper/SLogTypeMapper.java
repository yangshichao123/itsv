package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SLogType;

public interface SLogTypeMapper  extends BaseMapper<SLogType> {
    int insert(SLogType record);

    int insertSelective(SLogType record);

    SLogType selectByPrimaryId(Integer id);
}
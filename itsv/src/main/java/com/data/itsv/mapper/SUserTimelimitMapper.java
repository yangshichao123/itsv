package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SUserTimelimit;

public interface SUserTimelimitMapper extends BaseMapper<SUserTimelimit> {
    int insert(SUserTimelimit record);

    int insertSelective(SUserTimelimit record);
}
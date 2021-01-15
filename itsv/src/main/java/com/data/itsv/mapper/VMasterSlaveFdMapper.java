package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VMasterSlaveFd;

public interface VMasterSlaveFdMapper extends BaseMapper<VMasterSlaveFd> {
    int insert(VMasterSlaveFd record);

    int insertSelective(VMasterSlaveFd record);
}
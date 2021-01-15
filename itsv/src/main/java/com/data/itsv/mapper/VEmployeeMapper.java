package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VEmployee;

public interface VEmployeeMapper  extends BaseMapper<VEmployee> {
    int insert(VEmployee record);

    int insertSelective(VEmployee record);
}
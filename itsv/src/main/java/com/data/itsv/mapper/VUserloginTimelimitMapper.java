package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VUserloginTimelimit;

public interface VUserloginTimelimitMapper extends BaseMapper<VUserloginTimelimit> {
    int insert(VUserloginTimelimit record);

    int insertSelective(VUserloginTimelimit record);
}
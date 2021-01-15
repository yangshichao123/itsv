package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VMaskcamera;

public interface VMaskcameraMapper extends BaseMapper<VMaskcamera> {
    int insert(VMaskcamera record);

    int insertSelective(VMaskcamera record);
}
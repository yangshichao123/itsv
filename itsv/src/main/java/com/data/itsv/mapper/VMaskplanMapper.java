package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VMaskplan;

public interface VMaskplanMapper extends BaseMapper<VMaskplan> {
    int insert(VMaskplan record);

    int insertSelective(VMaskplan record);

    VMaskplan selectByPrimaryId(Integer id);
}
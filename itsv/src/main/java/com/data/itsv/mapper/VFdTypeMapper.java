package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VFdType;

public interface VFdTypeMapper extends BaseMapper<VFdType> {
    int insert(VFdType record);

    int insertSelective(VFdType record);

    VFdType selectByPrimaryId(Integer id);
}
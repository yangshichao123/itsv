package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRole;

public interface SRoleMapper extends BaseMapper<SRole> {
    int insert(SRole record);

    int insertSelective(SRole record);

    SRole selectByPrimaryId(Integer id);
}
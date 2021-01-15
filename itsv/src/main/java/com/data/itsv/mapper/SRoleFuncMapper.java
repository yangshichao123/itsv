package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRoleFunc;

public interface SRoleFuncMapper extends BaseMapper<SRoleFunc> {
    int insert(SRoleFunc record);

    int insertSelective(SRoleFunc record);

    SRoleFunc selectByPrimaryId(Integer roleId);
}
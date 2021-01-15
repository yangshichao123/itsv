package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.AuthUserRole;

public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {
    int insert(AuthUserRole record);

    int insertSelective(AuthUserRole record);

    AuthUserRole selectByPrimaryId(Integer id);

    int deleteByUniqueKey(AuthUserRole authUserRole);
}
package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.AuthRoleResource;

public interface AuthRoleResourceMapper extends BaseMapper<AuthRoleResource> {
    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    AuthRoleResource selectByPrimaryId(Integer id);

    int deleteByUniqueKey(Integer roleId, Integer resourceId);
}
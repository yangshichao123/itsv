package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.AuthRole;

import java.util.List;

public interface AuthRoleMapper extends BaseMapper<AuthRole> {
    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryId(Integer id);

    List<AuthRole> selectRoles();
}
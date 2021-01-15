package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.AuthResource;
import com.data.itsv.shiro.rule.RolePermRule;

import java.util.List;

public interface AuthResourceMapper extends BaseMapper<AuthResource> {
    int insert(AuthResource record);

    int insertSelective(AuthResource record);

    AuthResource selectByPrimaryId(Integer id);

    List<RolePermRule> selectRoleRules();

    List<AuthResource> selectAuthorityMenusByUid(String appId);

    List<AuthResource> selectMenus();

    List<AuthResource> selectApiTeamList();

    List<AuthResource> selectApiList();

    List<AuthResource> selectApiListByTeamId(Integer teamId);

    List<AuthResource> selectApisByRoleId(Integer roleId);

    List<AuthResource> selectMenusByRoleId(Integer roleId);

    List<AuthResource> selectNotAuthorityApisByRoleId(Integer roleId);

    List<AuthResource> selectNotAuthorityMenusByRoleId(Integer roleId);
}
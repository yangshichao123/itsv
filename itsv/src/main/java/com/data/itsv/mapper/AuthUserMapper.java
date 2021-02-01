package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.AuthUser;

import java.util.List;

public interface AuthUserMapper extends BaseMapper<AuthUser> {
    int insert(AuthUser record);

    int insertSelective(AuthUser record);

    AuthUser selectByPrimaryId(String id);

    AuthUser selectByUniqueKey(String userName);

    String selectUserRoles(String userName);

    List<AuthUser> selectUserList();

    List<AuthUser> selectUserListByRoleId(Integer roleId);

    List<AuthUser> selectUserListExtendByRoleId(Integer roleId);

    /**
     * 获取最大id
     * @return
     */
    int getMixId();
}
package com.data.itsv.service;

import com.data.itsv.model.AuthRole;

import java.util.List;

public interface AuthRoleService {
    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean addRole(AuthRole role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    boolean updataRole(AuthRole role);

    /**
     * 删除角色
     * @param role
     * @return
     */
    boolean deleteRole(AuthRole role);

    /**
     * 获取角色信息
     * @param role
     * @return
     */
    List<AuthRole> getRole(AuthRole role);
}

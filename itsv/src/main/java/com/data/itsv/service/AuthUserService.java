package com.data.itsv.service;

import com.data.itsv.model.AuthUser;

import java.util.List;

public interface AuthUserService {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean  addUser(AuthUser user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(AuthUser user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    boolean deleteUser(AuthUser user);

    /**
     * 查询用户信息
     * @param user
     * @return
     */
    List<AuthUser> getUser(AuthUser user);
}

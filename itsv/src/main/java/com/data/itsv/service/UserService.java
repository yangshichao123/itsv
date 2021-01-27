package com.data.itsv.service;



import com.data.itsv.model.AuthUser;
import com.data.itsv.model.SUser;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tomsun28
 * @date 21:14 2018/3/17
 */
public interface UserService {

    /**
     * description TODO
     *
     * @param id 1
     * @return java.lang.String
     */
    String loadAccountRole(String id);

    /**
     * description TODO
     *
     * @return java.util.List<com.usthe.bootshiro.domain.bo.AuthUser>
     */
    List<AuthUser> getUserList();

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.usthe.bootshiro.domain.bo.AuthUser>
     */
    List<AuthUser> getUserListByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param id 1
     * @param roleId 2
     * @return boolean
     */
    boolean authorityUserRole(String id, int roleId);

    /**
     * description TODO
     *
     * @param id 1
     * @param roleId 2
     * @return boolean
     */
    boolean deleteAuthorityUserRole(String id,int roleId);

    /**
     * description TODO
     *
     * @param userName 1
     * @return com.usthe.bootshiro.domain.bo.AuthUser
     */
    AuthUser getUserByAppId(String userName);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.usthe.bootshiro.domain.bo.AuthUser>
     */
    List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId);

    /**
     * 用户登陆
     * @param account
     * @param password
     * @return
     */
    SUser cuUserLogin(String account, String password);


    /**
     * 通过告警资源获取用户信息
     * @param s
     * @return
     */
    List<SUser>  getUsersByAlarmResId(String s);

    void userRegister(ChannelHandlerContext session, String userId);

    void userKeepAlive(ChannelHandlerContext session, String userId);

    void orderAlarmData(ChannelHandlerContext session, String userId);

    void reportUserState(String userCode, int state, String time);

    boolean updateUserState(String userCode, int state, String time);
}

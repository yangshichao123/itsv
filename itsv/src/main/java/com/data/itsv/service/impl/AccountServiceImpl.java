package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthUserMapper;
import com.data.itsv.model.AuthUser;
import com.data.itsv.model.vo.Account;
import com.data.itsv.service.AccountService;
import com.data.itsv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author tomsun28
 * @date 22:04 2018/3/7
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public Account loadAccount(String userName) throws DataAccessException {
        AuthUser user = userMapper.selectByUniqueKey(userName);
        return user != null ? new Account(user.getUsername(),user.getPassword(),user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(String userName) {
        AuthUser authUser=new AuthUser();
        authUser.setUsername(userName);
        AuthUser user = userMapper.selectOne(authUser);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) throws DataAccessException {

        int i = userMapper.insertSelective(account);
        // 给新用户授权访客角色
        boolean b = userService.authorityUserRole(account.getId() + "", 100);

        return  b;
    }

    @Override
    public String loadAccountRole(String userName) throws DataAccessException {

        return userMapper.selectUserRoles(userName);
    }


}

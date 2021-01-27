package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthUserMapper;
import com.data.itsv.model.AuthUser;
import com.data.itsv.service.AuthUserService;
import com.data.itsv.util.CommonUtil;
import com.data.itsv.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;
    @Override
    public boolean addUser(AuthUser user) {
        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        user.setPassword(Md5Util.md5(user.getPassword() + salt));
        user.setSalt(salt);
        user.setCreateTime(new Date());
        Byte j=1;
        user.setStatus(j);
        int i = authUserMapper.insertSelective(user);
        return i>0?true:false;
    }

    @Override
    public boolean updateUser(AuthUser user) {
        return false;
    }

    @Override
    public boolean deleteUser(AuthUser user) {
        return false;
    }

    @Override
    public List<AuthUser> getUser(AuthUser user) {
        return null;
    }
}

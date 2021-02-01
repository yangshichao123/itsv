package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthUserMapper;
import com.data.itsv.mapper.AuthUserRoleMapper;
import com.data.itsv.mapper.SUserOrderAlarmResMapper;
import com.data.itsv.mapper.SUserTimelimitMapper;
import com.data.itsv.model.*;
import com.data.itsv.service.AuthUserService;
import com.data.itsv.util.CommonUtil;
import com.data.itsv.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private static final String code="01010107013005";

    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    @Autowired
    private SUserOrderAlarmResMapper sUserOrderAlarmResMapper;
    @Autowired
    private SUserTimelimitMapper sUserTimelimitMapper;
    @Override
    public boolean  addUser(AuthUser user) {
        int i=0;
        synchronized (this){
           int id= authUserMapper.getMixId();

            String userCode = this.getUserCode(id);
            String salt = CommonUtil.getRandomString(6);
            // 存储到数据库的密码为 MD5(原密码+盐值)
            user.setPassword(Md5Util.md5(user.getPassword() + salt));
            user.setSalt(salt);
            user.setCreateTime(new Date());
            Byte j=1;
            user.setStatus(j);
            user.setCode(code+userCode);
            i = authUserMapper.insertSelective(user);
        }
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(Integer.parseInt(user.getRoleId()));
        authUserRole.setUserId(user.getId());
        authUserRole.setCreateTime(new Date());
        authUserRole.setUpdateTime(new Date());
        authUserRoleMapper.insert(authUserRole);
        return i>0?true:false;
    }
    private String getUserCode(int id){
        String idstar=id+"";
        if(idstar.length()<6){
            for(int i=1;i<6;i++){
                if((idstar.length()+1)==i){
                   idstar= 0+idstar;
                }
            }
        }
        return idstar;
    }


    @Override
    public boolean updateUser(AuthUser user) {
        if(!StringUtils.isEmpty(user.getPassword())){
            String salt = CommonUtil.getRandomString(6);
            // 存储到数据库的密码为 MD5(原密码+盐值)
            user.setPassword(Md5Util.md5(user.getPassword() + salt));
            user.setSalt(salt);
        }
        user.setUpdateTime(new Date());
        Byte j=1;
        int i = authUserMapper.updateByPrimaryKeySelective(user);
        return i>0?true:false;
    }

    @Override
    public boolean deleteUser(AuthUser user) {
        int i = authUserMapper.deleteByPrimaryKey(user);
        if(i>0){
            AuthUserRole authUserRole=new AuthUserRole();
            authUserRole.setUserId(user.getId());
            authUserRoleMapper.delete(authUserRole);

            SUserOrderAlarmRes sUserOrderAlarmRes=new SUserOrderAlarmRes();
            sUserOrderAlarmRes.setUserCode(user.getId()+"");
            sUserOrderAlarmResMapper.delete(sUserOrderAlarmRes);

            SUserTimelimit sUserTimelimit=new SUserTimelimit();
            sUserTimelimit.setUserCode(user.getId()+"");
            sUserTimelimitMapper.delete(sUserTimelimit);
        }
        return i>0?true:false;
    }

    @Override
    public List<AuthUser> getUser(AuthUser user) {
        Example example = new Example(AuthRole.class);
        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(user.getRoleId())){
            criteria.andEqualTo("roleId",user.getRoleId());
        }
        if(!StringUtils.isEmpty(user.getUsername())){
            criteria.andLike("username",user.getUsername());
        }
        if(!StringUtils.isEmpty(user.getCode())){
            criteria.andEqualTo("code",user.getCode());
        }
        List<AuthUser> authUsers = authUserMapper.selectByExample(example);
        return authUsers;
    }
}

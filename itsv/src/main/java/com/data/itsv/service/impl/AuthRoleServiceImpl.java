package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthRoleMapper;
import com.data.itsv.mapper.AuthUserRoleMapper;
import com.data.itsv.mapper.SRoleDirPrivilegeMapper;
import com.data.itsv.model.AuthRole;
import com.data.itsv.model.AuthUserRole;
import com.data.itsv.model.SRoleDirPrivilege;
import com.data.itsv.model.VDirectory;
import com.data.itsv.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
@Service
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    @Autowired
    private SRoleDirPrivilegeMapper sRoleDirPrivilegeMapper;
    
    @Override
    public boolean addRole(AuthRole role) {
        role.setCreateTime(new Date());
        int i = authRoleMapper.insertSelective(role);
        return i>0?true:false;
    }

    @Override
    public boolean updataRole(AuthRole role) {
        role.setUpdateTime(new Date());
        int i = authRoleMapper.updateByPrimaryKey(role);
        return i>0?true:false;
    }

    @Override
    public boolean deleteRole(AuthRole role) {
        int i = authRoleMapper.deleteByPrimaryKey(role);
        if(i>0){
            AuthUserRole authUserRole=new AuthUserRole();
            authUserRole.setRoleId(role.getId());
            authUserRoleMapper.delete(authUserRole);

            SRoleDirPrivilege sRoleDirPrivilege=new SRoleDirPrivilege();
            sRoleDirPrivilege.setRoleId(role.getId());
            sRoleDirPrivilegeMapper.delete(sRoleDirPrivilege);
        }
        return i>0?true:false;
    }

    @Override
    public List<AuthRole> getRole(AuthRole role) {
        Example example = new Example(AuthRole.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(role.getName())){

            criteria.andLike("name",role.getName());
        }
        List<AuthRole> authRoles = authRoleMapper.selectByExample(example);
        return authRoles;
    }
}

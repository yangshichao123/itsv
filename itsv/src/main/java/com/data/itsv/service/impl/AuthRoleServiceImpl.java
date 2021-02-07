package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthRoleMapper;
import com.data.itsv.mapper.AuthUserRoleMapper;
import com.data.itsv.mapper.SRoleDirPrivilegeMapper;
import com.data.itsv.mapper.VDirectoryMapper;
import com.data.itsv.model.AuthRole;
import com.data.itsv.model.AuthUserRole;
import com.data.itsv.model.SRoleDirPrivilege;
import com.data.itsv.model.VDirectory;
import com.data.itsv.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    @Autowired
    private VDirectoryMapper vDirectoryMapper;
    
    @Override
    public boolean addRole(AuthRole role) {
        role.setCode("role_admin");
        role.setCreateTime(new Date());
        int i = authRoleMapper.insertSelective(role);
        if (i>0) {
            Example example = new Example(VDirectory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("fullPath","%"+role.getDirectoryId()+"%");
            example.setOrderByClause("DIR_LEVEL desc");
            List<VDirectory> vDirectories = vDirectoryMapper.selectByExample(example);
            String dir=vDirectories.get(0).getFullPath();
            String[] dirs=dir.split(",");
            List<SRoleDirPrivilege > list=new ArrayList<>();
            for (String s : dirs) {
                if(!"0".equals(s)){
                    SRoleDirPrivilege sRoleDirPrivilege=new SRoleDirPrivilege();
                    sRoleDirPrivilege.setRoleId(role.getId());
                    sRoleDirPrivilege.setDirId(Integer.parseInt(s));
                    list.add(sRoleDirPrivilege);
                }
            }
            sRoleDirPrivilegeMapper.insertList(list);
        }
        return i>0?true:false;
    }

    @Override
    public boolean updataRole(AuthRole role) {
        role.setUpdateTime(new Date());
        int i = authRoleMapper.updateByPrimaryKeySelective(role);
        if(i>0){
            SRoleDirPrivilege sRoleDirPrivilege=new SRoleDirPrivilege();
            sRoleDirPrivilege.setRoleId(role.getId());
            sRoleDirPrivilegeMapper.delete(sRoleDirPrivilege);

            Example example = new Example(VDirectory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("fullPath","%"+role.getDirectoryId()+"%");
            example.setOrderByClause("DIR_LEVEL desc");
            List<VDirectory> vDirectories = vDirectoryMapper.selectByExample(example);
            String dir=vDirectories.get(0).getFullPath();
            String[] dirs=dir.split(",");
            List<SRoleDirPrivilege > list=new ArrayList<>();
            for (String s : dirs) {
                if(!"0".equals(s)){
                    SRoleDirPrivilege sRoleDirPrivilege1=new SRoleDirPrivilege();
                    sRoleDirPrivilege1.setRoleId(role.getId());
                    sRoleDirPrivilege1.setDirId(Integer.parseInt(s));
                    list.add(sRoleDirPrivilege1);
                }
            }
            sRoleDirPrivilegeMapper.insertList(list);
        }
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

            criteria.andLike("name","%"+role.getName()+"%");
        }
        List<AuthRole> authRoles = authRoleMapper.selectByExample(example);
        return authRoles;
    }
}

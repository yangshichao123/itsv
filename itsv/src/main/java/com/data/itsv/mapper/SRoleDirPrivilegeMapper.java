package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRoleDirPrivilege;

public interface SRoleDirPrivilegeMapper extends BaseMapper<SRoleDirPrivilege> {
    int insert(SRoleDirPrivilege record);

    int insertSelective(SRoleDirPrivilege record);
}
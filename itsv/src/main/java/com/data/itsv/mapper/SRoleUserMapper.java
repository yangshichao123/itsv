package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRoleUser;

public interface SRoleUserMapper extends BaseMapper<SRoleUser> {
    int insert(SRoleUser record);

    int insertSelective(SRoleUser record);
}
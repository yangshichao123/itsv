package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SContactUserGroup;

public interface SContactUserGroupMapper extends BaseMapper<SContactUserGroup> {
    int insert(SContactUserGroup record);

    int insertSelective(SContactUserGroup record);

    SContactUserGroup selectByPrimaryId(Integer id);
}
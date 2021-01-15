package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SContactUser;

public interface SContactUserMapper extends BaseMapper<SContactUser> {
    int insert(SContactUser record);

    int insertSelective(SContactUser record);

    SContactUser selectByPrimaryId(Integer id);
}
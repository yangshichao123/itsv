package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SOrg;

public interface SOrgMapper extends BaseMapper<SOrg> {
    int insert(SOrg record);

    int insertSelective(SOrg record);

    SOrg selectByPrimaryId(Integer id);
}
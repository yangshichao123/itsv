package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VResourceChange;

public interface VResourceChangeMapper extends BaseMapper<VResourceChange> {
    int insert(VResourceChange record);

    int insertSelective(VResourceChange record);

    VResourceChange selectByPrimaryId(Integer id);
}
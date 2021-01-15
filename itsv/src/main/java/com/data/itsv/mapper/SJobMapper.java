package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SJob;

public interface SJobMapper extends BaseMapper<SJob> {
    int insert(SJob record);

    int insertSelective(SJob record);

    SJob selectByPrimaryId(Integer id);
}
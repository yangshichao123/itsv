package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VResMap;

public interface VResMapMapper  extends BaseMapper<VResMap> {
    int insert(VResMap record);

    int insertSelective(VResMap record);
}
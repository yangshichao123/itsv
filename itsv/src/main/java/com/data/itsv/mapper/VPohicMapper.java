package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VPohic;

public interface VPohicMapper extends BaseMapper<VPohic> {
    int insert(VPohic record);

    int insertSelective(VPohic record);

    VPohic selectByPrimaryId(String code);
}
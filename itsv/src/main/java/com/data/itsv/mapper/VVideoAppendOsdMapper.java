package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideoAppendOsd;

public interface VVideoAppendOsdMapper extends BaseMapper<VVideoAppendOsd> {
    int insert(VVideoAppendOsd record);

    int insertSelective(VVideoAppendOsd record);
}
package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVssVideo;

public interface VVssVideoMapper  extends BaseMapper<VVssVideo> {
    int insert(VVssVideo record);

    int insertSelective(VVssVideo record);
}
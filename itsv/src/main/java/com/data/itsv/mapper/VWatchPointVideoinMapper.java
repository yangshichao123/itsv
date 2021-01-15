package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VWatchPointVideoin;

public interface VWatchPointVideoinMapper extends BaseMapper<VWatchPointVideoin> {
    int insert(VWatchPointVideoin record);

    int insertSelective(VWatchPointVideoin record);
}
package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SMapArea;
import com.data.itsv.model.SMapInfo;

public interface SMapInfoMapper extends BaseMapper<SMapInfo> {
    int insert(SMapInfo record);

    int insertSelective(SMapInfo record);
}
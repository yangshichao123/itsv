package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SMapArea;

public interface SMapAreaMapper extends BaseMapper<SMapArea> {
    int insert(SMapArea record);

    int insertSelective(SMapArea record);
}
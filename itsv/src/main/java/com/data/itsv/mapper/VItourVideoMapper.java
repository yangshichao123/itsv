package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VItourVideo;

public interface VItourVideoMapper extends BaseMapper<VItourVideo> {
    int insert(VItourVideo record);

    int insertSelective(VItourVideo record);
}
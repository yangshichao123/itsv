package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VIgroupVideo;

public interface VIgroupVideoMapper extends BaseMapper<VIgroupVideo> {
    int insert(VIgroupVideo record);

    int insertSelective(VIgroupVideo record);
}
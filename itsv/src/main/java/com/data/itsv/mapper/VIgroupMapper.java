package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VIgroup;

public interface VIgroupMapper extends BaseMapper<VIgroup> {
    int insert(VIgroup record);

    int insertSelective(VIgroup record);

    VIgroup selectByPrimaryId(Integer id);
}
package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VPlatformCascadeConfig;

public interface VPlatformCascadeConfigMapper extends BaseMapper<VPlatformCascadeConfig> {
    int insert(VPlatformCascadeConfig record);

    int insertSelective(VPlatformCascadeConfig record);
}
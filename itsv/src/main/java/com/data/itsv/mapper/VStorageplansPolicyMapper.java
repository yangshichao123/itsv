package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VStorageplansPolicy;

public interface VStorageplansPolicyMapper extends BaseMapper<VStorageplansPolicy> {
    int insert(VStorageplansPolicy record);

    int insertSelective(VStorageplansPolicy record);
}
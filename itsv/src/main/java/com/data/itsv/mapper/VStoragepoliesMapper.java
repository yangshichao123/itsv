package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VStoragepolies;

public interface VStoragepoliesMapper extends BaseMapper<VStoragepolies> {
    int insert(VStoragepolies record);

    int insertSelective(VStoragepolies record);

    VStoragepolies selectByPrimaryId(Integer id);
}
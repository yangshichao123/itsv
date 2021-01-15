package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideoStoreState;

public interface VVideoStoreStateMapper extends BaseMapper<VVideoStoreState> {
    int insert(VVideoStoreState record);

    int insertSelective(VVideoStoreState record);
}
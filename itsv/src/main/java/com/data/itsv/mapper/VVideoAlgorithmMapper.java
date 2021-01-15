package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideoAlgorithm;

public interface VVideoAlgorithmMapper extends BaseMapper<VVideoAlgorithm> {
    int insert(VVideoAlgorithm record);

    int insertSelective(VVideoAlgorithm record);
}
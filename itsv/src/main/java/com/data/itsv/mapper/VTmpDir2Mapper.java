package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VTmpDir2;

public interface VTmpDir2Mapper  extends BaseMapper<VTmpDir2> {
    int insert(VTmpDir2 record);

    int insertSelective(VTmpDir2 record);
}
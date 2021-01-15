package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VTmpDir;

public interface VTmpDirMapper  extends BaseMapper<VTmpDir> {
    int insert(VTmpDir record);

    int insertSelective(VTmpDir record);

    VTmpDir selectByPrimaryId(Integer id);
}
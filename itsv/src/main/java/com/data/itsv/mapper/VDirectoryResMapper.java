package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VDirectoryRes;

public interface VDirectoryResMapper  extends BaseMapper<VDirectoryRes> {
    int insert(VDirectoryRes record);

    int insertSelective(VDirectoryRes record);
}
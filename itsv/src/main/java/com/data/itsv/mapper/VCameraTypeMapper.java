package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VCameraType;

public interface VCameraTypeMapper extends BaseMapper<VCameraType> {
    int insert(VCameraType record);

    int insertSelective(VCameraType record);

    VCameraType selectByPrimaryId(Integer code);
}
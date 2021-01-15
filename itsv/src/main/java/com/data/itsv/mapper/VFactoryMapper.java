package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VFactory;

public interface VFactoryMapper extends BaseMapper<VFactory> {
    int insert(VFactory record);

    int insertSelective(VFactory record);

    VFactory selectByPrimaryId(String code);
}
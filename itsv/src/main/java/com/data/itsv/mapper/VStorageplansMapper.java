package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VStorageplans;

public interface VStorageplansMapper extends BaseMapper<VStorageplans> {
    int insert(VStorageplans record);

    int insertSelective(VStorageplans record);

    VStorageplans selectByPrimaryId(Integer id);
}
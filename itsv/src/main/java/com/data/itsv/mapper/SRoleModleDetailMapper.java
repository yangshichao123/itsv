package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SRoleModleDetail;

public interface SRoleModleDetailMapper extends BaseMapper<SRoleModleDetail> {
    int insert(SRoleModleDetail record);

    int insertSelective(SRoleModleDetail record);
}
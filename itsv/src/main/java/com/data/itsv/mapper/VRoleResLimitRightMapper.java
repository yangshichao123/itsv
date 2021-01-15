package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VRoleResLimitRight;

public interface VRoleResLimitRightMapper extends BaseMapper<VRoleResLimitRight> {
    int insert(VRoleResLimitRight record);

    int insertSelective(VRoleResLimitRight record);
}
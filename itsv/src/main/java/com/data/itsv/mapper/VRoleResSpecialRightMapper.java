package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VRoleResSpecialRight;

public interface VRoleResSpecialRightMapper  extends BaseMapper<VRoleResSpecialRight> {
    int insert(VRoleResSpecialRight record);

    int insertSelective(VRoleResSpecialRight record);
}
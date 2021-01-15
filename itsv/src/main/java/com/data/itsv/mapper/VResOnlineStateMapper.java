package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VResOnlineState;

public interface VResOnlineStateMapper extends BaseMapper<VResOnlineState> {
    int insert(VResOnlineState record);

    int insertSelective(VResOnlineState record);
}
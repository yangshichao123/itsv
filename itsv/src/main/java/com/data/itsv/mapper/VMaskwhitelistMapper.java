package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VMaskwhitelist;

public interface VMaskwhitelistMapper  extends BaseMapper<VMaskwhitelist> {
    int insert(VMaskwhitelist record);

    int insertSelective(VMaskwhitelist record);
}
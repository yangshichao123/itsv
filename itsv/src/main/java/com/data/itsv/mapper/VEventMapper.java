package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VEvent;

public interface VEventMapper  extends BaseMapper<VEvent> {
    int insert(VEvent record);

    int insertSelective(VEvent record);
}
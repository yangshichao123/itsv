package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAssPhoic;

public interface VAssPhoicMapper  extends BaseMapper<VAssPhoic> {
    int insert(VAssPhoic record);

    int insertSelective(VAssPhoic record);
}
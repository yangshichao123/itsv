package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VIlayoutDetail;

public interface VIlayoutDetailMapper  extends BaseMapper<VIlayoutDetail> {
    int insert(VIlayoutDetail record);

    int insertSelective(VIlayoutDetail record);
}
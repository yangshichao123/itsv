package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAnalyseFdChannel;

public interface VAnalyseFdChannelMapper extends BaseMapper<VAnalyseFdChannel> {
    int insert(VAnalyseFdChannel record);

    int insertSelective(VAnalyseFdChannel record);

    VAnalyseFdChannel selectByPrimaryId(String code);
}
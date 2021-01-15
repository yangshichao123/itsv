package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAnalyseFd;

public interface VAnalyseFdMapper extends BaseMapper<VAnalyseFd> {
    int insert(VAnalyseFd record);

    int insertSelective(VAnalyseFd record);

    VAnalyseFd selectByPrimaryId(String code);
}
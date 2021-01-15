package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAnalyseFdTemplate;

public interface VAnalyseFdTemplateMapper extends BaseMapper<VAnalyseFdTemplate> {
    int insert(VAnalyseFdTemplate record);

    int insertSelective(VAnalyseFdTemplate record);

    VAnalyseFdTemplate selectByPrimaryId(Integer id);
}
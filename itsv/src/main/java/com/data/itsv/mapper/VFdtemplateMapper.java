package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VFdtemplate;

import java.util.List;

public interface VFdtemplateMapper extends BaseMapper<VFdtemplate> {
    int insert(VFdtemplate record);

    int insertSelective(VFdtemplate record);

    VFdtemplate selectByPrimaryId(Integer id);

    List<VFdtemplate> getFdtemplate(VFdtemplate vFdtemplate);
}
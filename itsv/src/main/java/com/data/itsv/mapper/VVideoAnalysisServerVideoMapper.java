package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideoAnalysisServerVideo;

public interface VVideoAnalysisServerVideoMapper extends BaseMapper<VVideoAnalysisServerVideo> {
    int insert(VVideoAnalysisServerVideo record);

    int insertSelective(VVideoAnalysisServerVideo record);
}
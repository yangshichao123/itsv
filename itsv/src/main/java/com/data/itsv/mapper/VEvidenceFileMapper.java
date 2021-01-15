package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VEvidenceFile;

public interface VEvidenceFileMapper extends BaseMapper<VEvidenceFile> {
    int insert(VEvidenceFile record);

    int insertSelective(VEvidenceFile record);
}
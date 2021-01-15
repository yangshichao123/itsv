package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmEvidence;
import com.data.itsv.model.VAlarmIo;

public interface VAlarmIoMapper extends BaseMapper<VAlarmIo> {
    int insert(VAlarmIo record);

    int insertSelective(VAlarmIo record);
}
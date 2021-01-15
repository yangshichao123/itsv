package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SUserTimelimit;
import com.data.itsv.model.VAlarmEvidence;
import com.data.itsv.model.vo.VAlarmEvidenceVo;

import java.util.List;

public interface VAlarmEvidenceMapper  extends BaseMapper<VAlarmEvidence> {
    int insert(VAlarmEvidence record);

    int insertSelective(VAlarmEvidence record);

    List<VAlarmEvidenceVo> getAlarmEvidence(String alarmId);
}
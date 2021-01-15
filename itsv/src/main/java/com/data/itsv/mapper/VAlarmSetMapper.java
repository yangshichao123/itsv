package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VAlarmSet;
import com.data.itsv.model.vo.VAlarmSetVo;

import java.util.ArrayList;

public interface VAlarmSetMapper  extends BaseMapper<VAlarmSet> {
    int insert(VAlarmSet record);

    int insertSelective(VAlarmSet record);

    VAlarmSet selectByPrimaryId(String alarmResId);

    /**
     * 根据code查询用户订阅告警资源
     * @param code
     * @return
     */
    ArrayList<VAlarmSetVo> getAlarmResByUserCode(String code);
}
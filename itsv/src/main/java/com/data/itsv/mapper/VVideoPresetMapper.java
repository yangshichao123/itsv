package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideoPreset;

public interface VVideoPresetMapper extends BaseMapper<VVideoPreset> {
    int insert(VVideoPreset record);

    int insertSelective(VVideoPreset record);

    VVideoPreset selectByPrimaryId(Integer id);

    int saveVideoPreset(VVideoPreset vVideoPreset);
}
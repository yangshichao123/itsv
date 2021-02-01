package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VFdevice;
import com.data.itsv.model.vo.VFdeviceVo;

import java.util.List;

public interface VFdeviceMapper extends BaseMapper<VFdevice> {
    int insert(VFdevice record);

    int insertSelective(VFdevice record);

    VFdevice selectByPrimaryId(String code);

    List<VFdeviceVo> getFD(String pUserId, String fdTemplateCode, String longitude, String latitude, String code, String serverCode,String dirId,String name);

}
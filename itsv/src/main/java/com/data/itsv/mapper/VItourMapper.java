package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VItour;
import com.data.itsv.model.vo.VIlayoutVo;

import java.util.List;

public interface VItourMapper extends BaseMapper<VItour> {
    int insert(VItour record);

    int insertSelective(VItour record);

    VItour selectByPrimaryId(Integer code);


}
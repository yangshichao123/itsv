package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VIlayout;
import com.data.itsv.model.vo.VIlayoutVo;

import java.util.List;

public interface VIlayoutMapper extends BaseMapper<VIlayout> {
    int insert(VIlayout record);

    int insertSelective(VIlayout record);

    VIlayout selectByPrimaryId(Integer id);

    List<VIlayoutVo> selectVIlayouVo(String id, String layoutId, String id1);

    void saveVIlayout(VIlayout vIlayout);
}
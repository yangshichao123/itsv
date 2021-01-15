package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VVideo;
import com.data.itsv.model.vo.VVideoVo;

import java.util.ArrayList;
import java.util.List;

public interface VVideoMapper extends BaseMapper<VVideo> {
    int insert(VVideo record);

    int insertSelective(VVideo record);

    VVideo selectByPrimaryId(Integer id);

    List<VVideoVo> getVideoByUidDirId(String useId, String dirId, String type);

    List<VVideoVo> getVideoByTourId(String useId, String tourId);

    VVideo selectChannelNum(VVideo vVideo);

    ArrayList<VVideoVo> getVideo(VVideo vVideo);
}
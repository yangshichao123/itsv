package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VDirectory;

import java.util.List;

public interface VDirectoryMapper extends BaseMapper<VDirectory> {
    int insert(VDirectory record);

    int insertSelective(VDirectory record);

    VDirectory selectByPrimaryId(Integer id);

    List<VDirectory> getDirByUserId(String useId);
}
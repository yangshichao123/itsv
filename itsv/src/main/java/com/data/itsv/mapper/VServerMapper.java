package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VServer;

import java.util.List;

public interface VServerMapper extends BaseMapper<VServer> {
    int insert(VServer record);

    int insertSelective(VServer record);

    VServer selectByPrimaryId(Integer id);

    List<VServer> getServer(VServer vServer);
}
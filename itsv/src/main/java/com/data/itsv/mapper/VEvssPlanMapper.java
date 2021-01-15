package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.VEvssPlan;
import org.apache.ibatis.annotations.Param;

public interface VEvssPlanMapper extends BaseMapper<VEvssPlan> {
    int insert(VEvssPlan record);

    int insertSelective(VEvssPlan record);

    VEvssPlan selectByPrimaryId(@Param("videoCode") String videoCode, @Param("evssServerCode") String evssServerCode);
}
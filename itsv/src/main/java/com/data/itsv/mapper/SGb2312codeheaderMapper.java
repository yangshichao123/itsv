package com.data.itsv.mapper;

import com.data.itsv.base.BaseMapper;
import com.data.itsv.model.SContactUser;
import com.data.itsv.model.SGb2312codeheader;

public interface SGb2312codeheaderMapper extends BaseMapper<SGb2312codeheader> {
    int insert(SGb2312codeheader record);

    int insertSelective(SGb2312codeheader record);

    SGb2312codeheader selectByPrimaryId(Integer id);
}
package com.data.itsv.service.impl;

import com.data.itsv.mapper.VItourMapper;
import com.data.itsv.model.VItour;
import com.data.itsv.model.vo.VIlayoutVo;
import com.data.itsv.service.VItourService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("VItourService")
public class VItourServiceImpl implements VItourService {

    @Autowired
    private VItourMapper vItourMapper;
    @Override
    public List<VItour> getTour(String useId) {
        VItour vItour=new VItour();
        vItour.setUserCode(useId);
        List<VItour> select = vItourMapper.select(vItour);
        return select;
    }


}

package com.data.itsv.service;

import com.data.itsv.model.VItour;
import com.data.itsv.model.vo.VIlayoutVo;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public interface VItourService {
    List<VItour> getTour(String useId);


}

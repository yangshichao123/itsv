package com.data.itsv.service.impl;

import com.data.itsv.mapper.VIlayoutDetailMapper;
import com.data.itsv.mapper.VIlayoutMapper;
import com.data.itsv.model.VIlayout;
import com.data.itsv.model.VIlayoutDetail;
import com.data.itsv.model.vo.VIlayoutVo;
import com.data.itsv.service.VIlayoutService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

@Service("VIlayoutService")
public class VIlayoutServiceImpl implements VIlayoutService {
    @Autowired
    private VIlayoutMapper vIlayoutMapper;
    @Resource
    private VIlayoutDetailMapper vIlayoutDetailMapper;
    @Override
    public PageInfo<VIlayoutVo> getILayout(String id, String layoutId, String id1, String pageSize, String pageIndex) {
        PageHelper.startPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        PageInfo<VIlayoutVo> pageInfo = new PageInfo<>(vIlayoutMapper.selectVIlayouVo(id,layoutId,id1));
        pageInfo.getTotal();
        return pageInfo;
    }

    @Override
    public String addILayout(String userId, String userId1, String s, String layoutName, String s1) {
        VIlayout vIlayout=new VIlayout();
        vIlayout.setLayoutName(layoutName);
        vIlayout.setUserCode(userId1);
        vIlayoutMapper.saveVIlayout(vIlayout);
        return vIlayout.getId()+"";
    }

    @Override
    public boolean delILayout(String userId, String layoutId) {
        int i = vIlayoutMapper.deleteByPrimaryKey(Integer.parseInt(layoutId));
        return i>0?true:false;
    }

    @Override
    public boolean updateILayout(String userId, String userId1, String layoutId, String s, String layoutName, String s1) {
        VIlayout vIlayout=new VIlayout();
        vIlayout.setLayoutName(layoutName);
        vIlayout.setUserCode(userId1);
        vIlayout.setId(Integer.parseInt(layoutId));
        int i = vIlayoutMapper.updateByPrimaryKey(vIlayout);
        return i>0?true:false;
    }

    @Override
    public boolean addILayoutDetail(String userId, String screenNum, ArrayList<Map> iLayoutList) {

        for (Map map : iLayoutList) {
            VIlayoutDetail vIlayoutDetail=new VIlayoutDetail();
            vIlayoutDetail.setLayoutId(Integer.parseInt((String) map.get("iLayoutId")));
            vIlayoutDetail.setVideoCode((String) map.get("videoCode"));
            vIlayoutDetail.setLocationNum(Integer.parseInt((String)map.get("locationNum")));
            vIlayoutDetailMapper.insertSelective(vIlayoutDetail);
        }
        return false;
    }
}

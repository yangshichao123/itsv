package com.data.itsv.service.impl;

import com.data.itsv.mapper.VVideoPresetMapper;
import com.data.itsv.model.VVideoPreset;
import com.data.itsv.service.VVideoPresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("VVideoPresetService")
public class VVideoPresetServiceImpl implements VVideoPresetService {

    @Autowired
    private VVideoPresetMapper videoPresetMapper;
    @Override
    public List<VVideoPreset> getVideoPreset(String useId, String resId) {
        VVideoPreset vVideoPreset=new VVideoPreset();
        vVideoPreset.setVideoCode(resId);
        List<VVideoPreset> select = videoPresetMapper.select(vVideoPreset);

        return select;
    }

    @Override
    public String addVideoPreset(String useId, String resId, String presetName, String keepWatch, String presetIndex) {
        VVideoPreset vVideoPreset=new VVideoPreset();
        vVideoPreset.setVideoCode(resId);
        vVideoPreset.setKeepWatchFlag(Integer.parseInt(keepWatch));
        vVideoPreset.setIndexNum(Integer.parseInt(presetIndex));
        videoPresetMapper.saveVideoPreset(vVideoPreset);
        return vVideoPreset.getId()>0?"true":"false";
    }

    @Override
    public boolean delVideoPreset(String useId, String presetId) {
        int i = videoPresetMapper.deleteByPrimaryKey(Integer.parseInt(presetId));
        return i>0?true:false;
    }

    @Override
    public boolean updateVideoPreset(String useId, String presetId, String presetName, String keepWatch) {
        VVideoPreset vVideoPreset=new VVideoPreset();
        vVideoPreset.setId(Integer.parseInt(presetId));
        vVideoPreset.setName(presetName);
        vVideoPreset.setKeepWatchFlag(Integer.parseInt(keepWatch));
        int i = videoPresetMapper.updateByPrimaryKeySelective(vVideoPreset);
        return i>0?true:false;
    }
}

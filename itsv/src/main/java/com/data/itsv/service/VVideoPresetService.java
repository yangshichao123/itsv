package com.data.itsv.service;

import com.data.itsv.model.VVideoPreset;

import java.util.List;

public interface VVideoPresetService {
    List<VVideoPreset> getVideoPreset(String useId, String resId);

    String addVideoPreset(String useId, String resId, String presetName, String keepWatch, String presetIndex);

    boolean delVideoPreset(String useId, String presetId);

    boolean updateVideoPreset(String useId, String presetId, String presetName, String keepWatch);
}

package com.data.itsv.service;

import com.data.itsv.model.vo.VIlayoutVo;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Map;

public interface VIlayoutService {
    PageInfo<VIlayoutVo> getILayout(String id, String layoutId, String id1, String pageSize, String s);

    /**
     * 添加我得布局
     * @param userId
     * @param userId1
     * @param s
     * @param layoutName
     * @param s1
     * @return
     */
    String addILayout(String userId, String userId1, String s, String layoutName, String s1);

    /**
     * 删除我得布局
     * @param userId
     * @param layoutId
     * @return
     */
    boolean delILayout(String userId, String layoutId);

    /**
     * 跟新我得布局
     * @param userId
     * @param userId1
     * @param layoutId
     * @param s
     * @param layoutName
     * @param s1
     * @return
     */
    boolean updateILayout(String userId, String userId1, String layoutId, String s, String layoutName, String s1);

    /**
     * 跟新我得布局详情
     * @param userId
     * @param screenNum
     * @param iLayoutList
     * @return
     */
    boolean addILayoutDetail(String userId, String screenNum, ArrayList<Map> iLayoutList);
}

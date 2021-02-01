package com.data.itsv.service;

import com.data.itsv.model.VFdevice;
import com.data.itsv.model.VFdtemplate;
import com.data.itsv.model.vo.VFdeviceVo;

import java.util.List;

public interface VFdeviceService {
    /**
     * 获取设备模板信息
     * @param vFdtemplate
     * @return
     */
    List<VFdtemplate> getFdtemplate(VFdtemplate vFdtemplate);

    /**
     * 添加设备模板信息
     * @param vFdtemplate
     * @return
     */
    boolean addFdtemplate(VFdtemplate vFdtemplate);

    /**
     * 修改设备模板信息
     * @param vFdtemplate
     * @return
     */
    boolean updateFdtemplate(VFdtemplate vFdtemplate);

    /**
     * 删除设备模板信息
     * @param vFdtemplate
     * @return
     */
    boolean deleteFdtemplate(VFdtemplate vFdtemplate);

    /**
     * 添加设备信息
     * @param vFdevice
     * @return
     */
    boolean addFd(VFdeviceVo vFdevice);

    /**
     * 删除设备信息 根据id
     * @param vFdevice
     * @return
     */
    boolean deleteFd(VFdevice vFdevice);

    void noticeChangeDeviceResource(String opt, List<VFdevice> list);

    /**
     * 更新设备信息
     * @param vFdevice
     * @return
     */
    boolean updateFd(VFdeviceVo vFdevice);

    /**
     * 查询设备信息
     * @param vFdevice
     * @return
     */
    List<VFdeviceVo> getFd(VFdeviceVo vFdevice);
}

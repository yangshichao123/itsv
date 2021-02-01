package com.data.itsv.service;

import com.data.itsv.model.VDirectory;

import java.util.ArrayList;
import java.util.List;

public interface VDirectoryService {
    /**
     * 添加组织机构目录
     * @param directory
     * @return
     */
    VDirectory addDirectory(VDirectory directory);

    /**
     * 修改组织机构目录
     * @param directory
     * @return
     */
     boolean updateDirectory(VDirectory directory) ;

    /**
     * 删除组织机构目录
     * @param directory
     * @return
     */
     boolean deleteDirectory(VDirectory directory);

    /**
     * 根据用户权限获取目录
     * @param directory
     * @return
     */
     List<VDirectory> getDirectory(VDirectory directory);
}

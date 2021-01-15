package com.data.itsv.service;

import com.data.itsv.model.VDirectory;

import java.util.List;

public interface DirService {
    /**
     * 根据用户id 查询目录资源
     * @param useId
     * @return
     */
    String getDirByUserId(String useId);

    /**
     * 通过用户编号查询该用户自建目录树
     * @param useId
     * @return
     */
    String getIGroupTree(String useId);


}

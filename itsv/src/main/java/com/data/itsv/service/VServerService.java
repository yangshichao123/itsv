package com.data.itsv.service;

import com.data.itsv.model.VServer;

import java.util.List;

public interface VServerService {
    /**
     * 获取服务信息
     * @param vServer
     * @return
     */
    List<VServer> getServer(VServer vServer);

    /**
     * 添加服务信息
     * @param vServer
     * @return
     */
    boolean addServer(VServer vServer);

    /**
     * 删除服务
     * @param id
     * @return
     */
    boolean deleteServer(int id);

    /**
     * 修改服务
     * @param vServer
     * @return
     */
    boolean updateServer(VServer vServer);
}

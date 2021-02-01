package com.data.itsv.service.impl;

import com.data.itsv.mapper.VServerMapper;
import com.data.itsv.model.VServer;
import com.data.itsv.service.VServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VServerServiceImpl implements VServerService {
    @Autowired
    private VServerMapper vServerMapper;
    @Override
    public List<VServer> getServer(VServer vServer) {

        return vServerMapper.getServer(vServer);
    }

    @Override
    public boolean addServer(VServer vServer) {
        return vServerMapper.insertSelective(vServer)>0?true:false;
    }

    @Override
    public boolean deleteServer(int id) {
        return vServerMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean updateServer(VServer vServer) {
        return vServerMapper.updateByPrimaryKeySelective(vServer)>0?true:false;
    }
}

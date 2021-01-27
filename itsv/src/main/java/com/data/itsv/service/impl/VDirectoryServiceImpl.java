package com.data.itsv.service.impl;

import com.data.itsv.mapper.SRoleDirPrivilegeMapper;
import com.data.itsv.mapper.VDirectoryMapper;
import com.data.itsv.mapper.VDirectoryResMapper;
import com.data.itsv.model.SRoleDirPrivilege;
import com.data.itsv.model.VDirectory;
import com.data.itsv.model.VDirectoryRes;
import com.data.itsv.model.VVideo;
import com.data.itsv.service.VDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class VDirectoryServiceImpl implements VDirectoryService {

    @Autowired
    private VDirectoryMapper vDirectoryMapper;
    @Autowired
    private SRoleDirPrivilegeMapper sRoleDirPrivilegeMapper;
    @Autowired
    private VDirectoryResMapper vDirectoryResMapper;

    /**
     * 添加组织机构目录
     * @param directory
     * @return
     */
    public boolean accountLogin(VDirectory directory) {

        int i = vDirectoryMapper.insertSelective(directory);
        return i>0?true:false;
    }

    /**
     * 修改组织机构目录
     * @param directory
     * @return
     */
    public boolean updateDirectory(VDirectory directory) {
        int i = vDirectoryMapper.updateByPrimaryKeySelective(directory);
        return i>0?true:false;
    }

    /**
     * 删除组织机构目录
     * @param directory
     * @return
     */
    public boolean deleteDirectory(VDirectory directory) {
        Example example = new Example(VDirectory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("fullPath",directory.getId()+"");
        int i = vDirectoryMapper.deleteByExample(example);
        if(i>0){
            SRoleDirPrivilege sRoleDirPrivilege=new SRoleDirPrivilege();
            sRoleDirPrivilege.setDirId(directory.getId());
            sRoleDirPrivilegeMapper.delete(sRoleDirPrivilege);
            VDirectoryRes vDirectoryRes=new VDirectoryRes();
            vDirectoryRes.setDirectoryId(directory.getId());
            vDirectoryResMapper.delete(vDirectoryRes);
        }
        return i>0?true:false;
    }

    /**
     * 根据用户权限获取目录
     * @param directory
     * @return
     */
    public List<VDirectory> getDirectory(VDirectory directory) {
        List<VDirectory> vDirectoryList= vDirectoryMapper.getDirByUserId(directory.getUserId());
        List<VDirectory> vDirectories=new ArrayList<>();
        List<VDirectory> tree = this.getTree(vDirectories, vDirectoryList, 1, 0);
        return tree;
    }

    private List<VDirectory> getTree( List<VDirectory> vDirectories,List<VDirectory> vDirectoryList,int level,int parentId){

        for (VDirectory vDirectory : vDirectoryList) {
            Integer dirLevel = vDirectory.getDirLevel();
            if(dirLevel==level&&vDirectory.getParentId()==parentId){
                vDirectories.add(vDirectory);
            }else if(dirLevel == level+1){
                List<VDirectory> vDirectories1 =new ArrayList<>();
                vDirectories.forEach(vDirectory1 -> {
                    if(vDirectory1.getId().equals(vDirectory.getParentId()))
                        vDirectory1.setChildren(vDirectories1);
                });
                this.getTree(vDirectories1,vDirectoryList,dirLevel,vDirectory.getParentId());
            }
        }

        return  vDirectories;
    }
}

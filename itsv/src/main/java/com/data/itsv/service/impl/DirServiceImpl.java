package com.data.itsv.service.impl;

import com.data.itsv.mapper.VDirectoryMapper;
import com.data.itsv.model.VDirectory;
import com.data.itsv.service.DirService;
import com.data.itsv.util.Dom;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DirService")
public class DirServiceImpl implements DirService {

    @Autowired
    private VDirectoryMapper vDirectoryMapper;
    @Override
    public String getDirByUserId(String useId) {
       List<VDirectory> vDirectoryList= vDirectoryMapper.getDirByUserId(useId);
       Map map =new HashMap();
       Map<String,VDirectory> vDirectoryMap=new HashMap<>();
        Map tree = this.getTree(map, vDirectoryList, 1,0);
        for (VDirectory vDirectory : vDirectoryList) {
            vDirectoryMap.put(vDirectory.getId()+"",vDirectory);
        }
        String dir = Dom.getXml(tree, vDirectoryMap, "dir");
        return dir;
    }
    private Map getTree(Map map , List<VDirectory> vDirectoryList,int level,int parentId){
        List<VDirectory> vDirectories=new ArrayList<>();
        for (VDirectory vDirectory : vDirectoryList) {
            Integer dirLevel = vDirectory.getDirLevel();
            Map map1=new HashMap();
            map1.put(vDirectory.getId(),"");
            if(dirLevel==level&&vDirectory.getParentId()==parentId){
                map.put(vDirectory.getId(),map1);
            }else if(dirLevel == level+1){
                for (Object o : map.values()) {
                    Map map2=(Map) o;
                    if(map2.get(vDirectory.getParentId())!=null){
                        this.getTree(map2,vDirectories,dirLevel,vDirectory.getParentId());
                    }
                }
            }
        }

        return  map;
    }


    @Override
    public String getIGroupTree(String useId) {
        return null;
    }

}

package com.data.itsv.service.impl;

import com.data.itsv.mapper.*;
import com.data.itsv.model.*;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.netty.client.BootNettyChannelInboundHandlerAdapter;
import com.data.itsv.netty.vo.BaseMsg;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.VFdeviceService;
import com.data.itsv.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class VFdeviceServiceImpl implements VFdeviceService {
    @Autowired
    private VFdtemplateMapper vFdtemplateMapper;
    @Autowired
    private ProtocolRequest protocolRequest;
    @Autowired
    private VAlarmIoMapper vAlarmIoMapper;
    @Autowired
    private VideoService videoService;
    @Autowired
    private VServerMapper vServerMapper;
    @Autowired
    private VFdeviceMapper vFdeviceMapper;
    @Autowired
    private VResOnlineStateMapper vResOnlineStateMapper;
    @Autowired
    private VDirectoryResMapper vDirectoryResMapper;

    @Override
    public List<VFdtemplate> getFdtemplate(VFdtemplate vFdtemplate) {

        return vFdtemplateMapper.getFdtemplate(vFdtemplate);
    }

    @Override
    public boolean addFdtemplate(VFdtemplate vFdtemplate) {
        return vFdtemplateMapper.insertSelective(vFdtemplate) > 0 ? true : false;
    }

    @Override
    public boolean updateFdtemplate(VFdtemplate vFdtemplate) {
        return vFdtemplateMapper.updateByPrimaryKeySelective(vFdtemplate) > 0 ? true : false;
    }

    @Override
    public boolean deleteFdtemplate(VFdtemplate vFdtemplate) {
        return vFdtemplateMapper.deleteByPrimaryKey(vFdtemplate) > 0 ? true : false;
    }

    @Override
    public boolean addFd(VFdeviceVo vFdevice) {
        //查询服务信息
        VServer vServer = vServerMapper.selectByPrimaryId(Integer.parseInt(vFdevice.getServerCode()));
        //查询设备模板信息
        VFdtemplate vFdtemplate = vFdtemplateMapper.selectByPrimaryId(vFdevice.getFdtempleteId());
        //保存设备信息
        vFdeviceMapper.insertSelective(vFdevice);
        //修改设备code 根据主键自增
        VFdevice vFdevice1 = new VFdevice();
        vFdevice1.setCode(vFdevice.getServerCode().substring(10) + vFdtemplate.getFdtypeCode() + "5" + getUserCode(vFdevice.getId()));
        vFdevice1.setId(vFdevice.getId());
        //修改设备编号
        vFdeviceMapper.updateByPrimaryKeySelective(vFdevice1);
        //添加视频通道信息
        if (vFdtemplate != null && vFdtemplate.getVideoinNum() != null) {
            for (int i = 0; i < vFdtemplate.getVideoinNum(); i++) {
                videoService.addVideoIn(vFdevice.getName() + "_video_" + i, vFdevice1.getCode(), "" + i, "", "", "");
            }
        }
        //添加告警通道
        if (vFdtemplate != null && vFdtemplate.getAlarminNum() != null) {
            for (int i = 0; i < vFdtemplate.getAlarminNum(); i++) {

                VAlarmIo vAlarmIo = new VAlarmIo();
                vAlarmIo.setFdCode(vFdevice1.getCode());
                vAlarmIo.setIndexNum(i);
                vAlarmIo.setName(vFdevice.getName() + "_alarm_" + i);

                int i1 = vAlarmIoMapper.insertSelective(vAlarmIo);
                VAlarmIo vAlarmIo1 = new VAlarmIo();
                vAlarmIo1.setId(vAlarmIo.getId());
                vAlarmIo1.setCode(vAlarmIo.getFdCode().substring(10) + "1345" + this.getUserCode(vAlarmIo.getId()));
                vAlarmIoMapper.updateByPrimaryKeySelective(vAlarmIo1);
            }
        }
        //发送cms  添加设备
        List<VFdevice> list = new ArrayList<>();
        list.add(vFdevice);
        this.noticeChangeDeviceResource("1", list);

        //关联目录设备信息
        VDirectoryRes vDirectoryRes = new VDirectoryRes();
        vDirectoryRes.setResCode(vFdevice.getCode());
        vDirectoryRes.setDirectoryId(Integer.parseInt(vFdevice.getDirId()));
        vDirectoryRes.setType(1);
        vDirectoryResMapper.insertSelective(vDirectoryRes);
        return true;
    }

    private String getUserCode(int id) {
        String idstar = id + "";
        if (idstar.length() < 6) {
            for (int i = 1; i < 6; i++) {
                if ((idstar.length() + 1) == i) {
                    idstar = 0 + idstar;
                }
            }
        }
        return idstar;
    }

    @Override
    public boolean deleteFd(VFdevice vFdevice) {
        int i = vFdeviceMapper.deleteByPrimaryKey(vFdevice);
        if (i > 0) {
            //删除目录关联摄像机信息
            VDirectoryRes vDirectoryRes = new VDirectoryRes();
            vDirectoryRes.setResCode(vFdevice.getCode());
            vDirectoryResMapper.delete(vDirectoryRes);
            //删除摄像机通道信息
            videoService.deleteVideoByFDCode(vFdevice.getCode());
            //删除告警通道信息
            VAlarmIo vAlarmIo = new VAlarmIo();
            vAlarmIo.setFdCode(vFdevice.getCode());
            vAlarmIoMapper.delete(vAlarmIo);
            //删除设备是否在线类服务信息
            VResOnlineState vResOnlineState = new VResOnlineState();
            vResOnlineState.setResCode(vFdevice.getCode());
            vResOnlineState.setType(2);
            vResOnlineStateMapper.delete(vResOnlineState);
            //发送给cms 删除设备
            List<VFdevice> list = new ArrayList<>();
            list.add(vFdevice);
            this.noticeChangeDeviceResource("2", list);
        }
        return true;
    }

    /**
     * @author ghj
     * @说明：通知设备资源变化
     */
    @Override
    public void noticeChangeDeviceResource(String opt, List<VFdevice> list) {

        // 数据打包 操作类型，1增加2删除3修改
        BaseMsg bm = protocolRequest.changeDeviceResource(opt, list);
        // 发送到CMS
        try {
            BootNettyChannelInboundHandlerAdapter.sendData(bm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean updateFd(VFdeviceVo vFdevice) {
        int i = vFdeviceMapper.updateByPrimaryKeySelective(vFdevice);
        List<VFdevice> list = new ArrayList<>();
        list.add(vFdevice);
        this.noticeChangeDeviceResource("3", list);
        //跟新关联目录设备信息
        if (i > 0 && vFdevice.getDirId() != null && !"".equals(vFdevice.getDirId())) {
            Example example = new Example(VDirectoryRes.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("resCode", vFdevice.getCode());
            VDirectoryRes vDirectoryRes = new VDirectoryRes();
            vDirectoryRes.setDirectoryId(Integer.parseInt(vFdevice.getDirId()));
            vDirectoryResMapper.updateByExampleSelective(vDirectoryRes, example);
        }
        return i > 0 ? true : false;
    }

    @Override
    public List<VFdeviceVo> getFd(VFdeviceVo vFdevice) {
        return vFdeviceMapper.getFD("", "", "", "", vFdevice.getCode(), "", vFdevice.getDirId(), vFdevice.getName());
    }
}

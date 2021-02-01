package com.data.itsv.controller;

import com.data.itsv.model.*;
import com.data.itsv.model.vo.Message;
import com.data.itsv.model.vo.VFdeviceVo;
import com.data.itsv.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.VfsResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   post新增,get读取,put完整更新,patch部分更新,delete删除
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
@Api(value = "设备相关接口")
@RestController
@RequestMapping("/fd")
public class FdController extends BaseAction {

    @Autowired
    private VServerService vServerService;
    @Autowired
    private VFdeviceService vFdeviceService;


    @ApiOperation(value = "获取服务列表", notes = "POST请求json格式参数")
    @PostMapping("/server/getServer")
    @CrossOrigin
    public Message getServer(@RequestBody VServer vServer) {
        List<VServer> data =vServerService.getServer(vServer);
        return new Message().ok("success",data);
    }
    @ApiOperation(value = "添加服务", notes = "POST请求json格式参数")
    @PostMapping("/server/addServer")
    @CrossOrigin
    public Message addServer(@RequestBody VServer vServer) {
        boolean isTrue=vServerService.addServer(vServer);
        if (!isTrue)
            return new Message().error("添加服务失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "删除服务", notes = "POST请求json格式参数")
    @PostMapping("/server/deleteServer")
    @CrossOrigin
    public Message deleteServer(@RequestBody int id) {
        boolean isTrue=vServerService.deleteServer(id);
        if (!isTrue)
            return new Message().error("删除服务失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "修改服务", notes = "POST请求json格式参数")
    @PostMapping("/server/updateServer")
    @CrossOrigin
    public Message updateServer(@RequestBody VServer vServer) {
        boolean isTrue=vServerService.updateServer(vServer);
        if (!isTrue)
            return new Message().error("修改服务失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "获取设备模板信息", notes = "POST请求json格式参数")
    @PostMapping("/fdtemplate/getFdtemplate")
    @CrossOrigin
    public Message getFdtemplate(@RequestBody VFdtemplate vFdtemplate) {
        List<VFdtemplate> vFdtemplates=vFdeviceService.getFdtemplate(vFdtemplate);
        return new Message().ok("success",vFdtemplates);
    }
    @ApiOperation(value = "添加设备模板信息", notes = "POST请求json格式参数")
    @PostMapping("/fdtemplate/addFdtemplate")
    @CrossOrigin
    public Message addFdtemplate(@RequestBody VFdtemplate vFdtemplate) {
        boolean isTrue=vFdeviceService.addFdtemplate(vFdtemplate);
        if (!isTrue)
            return new Message().error("修改服务失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "修改设备模板信息", notes = "POST请求json格式参数")
    @PostMapping("/fdtemplate/updateFdtemplate")
    @CrossOrigin
    public Message updateFdtemplate(@RequestBody VFdtemplate vFdtemplate) {
        boolean isTrue=vFdeviceService.updateFdtemplate(vFdtemplate);
        if (!isTrue)
            return new Message().error("修改设备模板信息失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "删除设备模板信息", notes = "POST请求json格式参数")
    @PostMapping("/fdtemplate/deleteFdtemplate")
    @CrossOrigin
    public Message deleteFdtemplate(@RequestBody VFdtemplate vFdtemplate) {
        boolean isTrue=vFdeviceService.deleteFdtemplate(vFdtemplate);
        if (!isTrue)
            return new Message().error("删除设备模板信息失败");
        return new Message().ok("success","");
    }
    @ApiOperation(value = "添加设备", notes = "POST请求json格式参数")
    @PostMapping("/Fdevice/addFd")
    @CrossOrigin
    @Transactional
    public Message addFd(@RequestBody VFdeviceVo vFdevice) {
        boolean isTrue=vFdeviceService.addFd(vFdevice);
        if (!isTrue)
            return new Message().error("添加设备失败");
        return new Message().ok("success","");
    }

    @ApiOperation(value = "删除设备", notes = "POST请求json格式参数")
    @PostMapping("/Fdevice/deleteFd")
    @CrossOrigin
    @Transactional
    public Message deleteFd(@RequestBody VFdevice vFdevice) {
        boolean isTrue=vFdeviceService.deleteFd(vFdevice);
        if (!isTrue)
            return new Message().error("删除设备失败");
        return new Message().ok("success","");
    }

    @ApiOperation(value = "更新设备", notes = "POST请求json格式参数")
    @PostMapping("/Fdevice/updateFd")
    @CrossOrigin
    @Transactional
    public Message updateFd(@RequestBody VFdeviceVo vFdevice) {
        boolean isTrue=vFdeviceService.updateFd(vFdevice);
        if (!isTrue)
            return new Message().error("删除设备失败");
        return new Message().ok("success","");
    }

    @ApiOperation(value = "更新设备", notes = "POST请求json格式参数")
    @PostMapping("/Fdevice/getFd")
    @CrossOrigin
    @Transactional
    public Message getFd(@RequestBody VFdeviceVo vFdevice) {
        List<VFdeviceVo> list=vFdeviceService.getFd(vFdevice);
        if (list.size()<1)
            return new Message().error("查询设备失败");
        return new Message().ok("success",list);
    }



}

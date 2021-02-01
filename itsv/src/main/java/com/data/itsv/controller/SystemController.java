package com.data.itsv.controller;

import com.data.itsv.model.AuthRole;
import com.data.itsv.model.AuthUser;
import com.data.itsv.model.VDirectory;
import com.data.itsv.model.vo.Message;
import com.data.itsv.service.AuthRoleService;
import com.data.itsv.service.AuthUserService;
import com.data.itsv.service.VDirectoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *   post新增,get读取,put完整更新,patch部分更新,delete删除
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
@Api(value = "系统相关接口")
@RestController
@RequestMapping("/system")
public class SystemController extends BaseAction {





    @Autowired
    private VDirectoryService vDirectoryService;
    @Autowired
    private AuthRoleService authRoleService;
    @Autowired
    private AuthUserService authUserService;


    @Value("${bootshiro.enableEncryptPassword}")
    private boolean isEncryptPassword;

    @ApiOperation(value = "添加目录信息", notes = "POST请求")
    @PostMapping("/directory/addDirectory")
    @CrossOrigin
    @Transactional
    public Message addDirectory(@RequestBody VDirectory directory) {
        VDirectory directory1 =vDirectoryService.addDirectory(directory);
         return new Message().ok("success",directory1);
    }
    @ApiOperation(value = "修改目录信息", notes = "POST请求")
    @PostMapping("/directory/updateDirectory")
    @CrossOrigin
    public Message updateDirectory(@RequestBody VDirectory directory) {
        boolean isTrue =vDirectoryService.updateDirectory(directory);
        if(isTrue)
           return new Message().ok("success","");
        return new Message().error("修改组织机构失败");
    }
    @ApiOperation(value = "删除目录信息", notes = "POST请求")
    @PostMapping("/directory/deleteDirectory")
    @CrossOrigin
    @Transactional
    public Message deleteDirectory(@RequestBody VDirectory directory) {
        boolean isTrue =vDirectoryService.deleteDirectory(directory);
        if(isTrue)
           return new Message().ok("success","");
        return new Message().error("删除组织机构失败");
    }
    @ApiOperation(value = "获取目录信息", notes = "POST请求json格式参数")
    @PostMapping("/directory/getDirectory")
    @CrossOrigin
    public Message getDirectory(@RequestBody VDirectory directory) {
        List<VDirectory> data =vDirectoryService.getDirectory(directory);
        return new Message().ok("success",data);
    }
    @ApiOperation(value = "添加角色信息", notes = "POST请求json格式参数")
    @PostMapping("/role/addRole")
    @CrossOrigin
    @Transactional
    public Message addRole(@RequestBody AuthRole role) {
        boolean isTrue =authRoleService.addRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("添加角色失败");
    }
    @ApiOperation(value = "修改角色信息", notes = "POST请求json格式参数")
    @PostMapping("/role/updataRole")
    @CrossOrigin
    @Transactional
    public Message updataRole(@RequestBody AuthRole role) {
        boolean isTrue =authRoleService.updataRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("更新角色失败");
    }

    @ApiOperation(value = "删除角色信息", notes = "POST请求json格式参数")
    @PostMapping("/role/deleteRole")
    @CrossOrigin
    @Transactional
    public Message deleteRole(@RequestBody AuthRole role) {
        boolean isTrue =authRoleService.deleteRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("删除角色失败");
    }
    @ApiOperation(value = "获取角色信息", notes = "POST请求json格式参数")
    @PostMapping("/role/getRole")
    @CrossOrigin
    public Message getRole(@RequestBody AuthRole role) {
        List<AuthRole> authRoles=authRoleService.getRole(role);
        return new Message().ok("success",authRoles);
    }
    @ApiOperation(value = "添加用户信息", notes = "POST请求json格式参数")
    @PostMapping("/user/addUser")
    @CrossOrigin
    public Message addUser(@RequestBody AuthUser user) {
        boolean isTrue=authUserService.addUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("添加用户失败");
    }
    @ApiOperation(value = "修改用户信息", notes = "POST请求json格式参数")
    @PostMapping("/user/updateUser")
    @CrossOrigin
    public Message updateUser(@RequestBody AuthUser user) {
        boolean isTrue=authUserService.updateUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("修改用户失败");
    }
    @ApiOperation(value = "删除用户信息", notes = "POST请求json格式参数")
    @PostMapping("/user/deleteUser")
    @CrossOrigin
    public Message deleteUser(@RequestBody AuthUser user) {
        boolean isTrue=authUserService.deleteUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("删除用户失败");
    }
    @ApiOperation(value = "获取用户信息", notes = "POST请求json格式参数")
    @PostMapping("/user/getUser")
    @CrossOrigin
    public Message getUser(@RequestBody AuthUser user) {
        List<AuthUser> authUsers=authUserService.getUser(user);
         return new Message().ok("success",authUsers);
    }

}

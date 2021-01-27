package com.data.itsv.controller;

import com.data.itsv.model.AuthRole;
import com.data.itsv.model.AuthUser;
import com.data.itsv.model.VDirectory;
import com.data.itsv.model.vo.Message;
import com.data.itsv.service.AuthRoleService;
import com.data.itsv.service.AuthUserService;
import com.data.itsv.service.VDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *   post新增,get读取,put完整更新,patch部分更新,delete删除
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
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

    //@ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/directory/addDirectory")
    @CrossOrigin
    public Message accountLogin(VDirectory directory) {
        boolean isTrue =vDirectoryService.accountLogin(directory);
        if(isTrue)
           return new Message().ok("success","");
        return new Message().error("添加组织机构失败");
    }
    @PostMapping("/directory/updateDirectory")
    @CrossOrigin
    public Message updateDirectory(VDirectory directory) {
        boolean isTrue =vDirectoryService.updateDirectory(directory);
        if(isTrue)
           return new Message().ok("success","");
        return new Message().error("修改组织机构失败");
    }
    @PostMapping("/directory/deleteDirectory")
    @CrossOrigin
    @Transactional
    public Message deleteDirectory(VDirectory directory) {
        boolean isTrue =vDirectoryService.deleteDirectory(directory);
        if(isTrue)
           return new Message().ok("success","");
        return new Message().error("删除组织机构失败");
    }
    @PostMapping("/directory/getDirectory")
    @CrossOrigin
    public Message getDirectory(VDirectory directory) {
        List<VDirectory> data =vDirectoryService.getDirectory(directory);
        return new Message().ok("success",data);
    }

    @PostMapping("/role/addRole")
    @CrossOrigin
    public Message addRole(AuthRole role) {
        boolean isTrue =authRoleService.addRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("添加角色失败");
    }

    @PostMapping("/role/updataRole")
    @CrossOrigin
    public Message updataRole(AuthRole role) {
        boolean isTrue =authRoleService.updataRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("更新角色失败");
    }


    @PostMapping("/role/deleteRole")
    @CrossOrigin
    @Transactional
    public Message deleteRole(AuthRole role) {
        boolean isTrue =authRoleService.deleteRole(role);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("删除角色失败");
    }
    @PostMapping("/role/getRole")
    @CrossOrigin
    public Message getRole(AuthRole role) {
        List<AuthRole> authRoles=authRoleService.getRole(role);
        return new Message().ok("success",authRoles);
    }
    @PostMapping("/user/addUser")
    @CrossOrigin
    public Message addUser(AuthUser user) {
        boolean isTrue=authUserService.addUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("添加用户失败");
    }
    @PostMapping("/user/updateUser")
    @CrossOrigin
    public Message updateUser(AuthUser user) {
        boolean isTrue=authUserService.updateUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("修改用户失败");
    }
    @PostMapping("/user/deleteUser")
    @CrossOrigin
    public Message deleteUser(AuthUser user) {
        boolean isTrue=authUserService.deleteUser(user);
        if(isTrue)
            return new Message().ok("success","");
        return new Message().error("删除用户失败");
    }
    @PostMapping("/user/getUser")
    @CrossOrigin
    public Message getUser(AuthUser user) {
        List<AuthUser> authUsers=authUserService.getUser(user);
         return new Message().ok("success",authUsers);
    }

}

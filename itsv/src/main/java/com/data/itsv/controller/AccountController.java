package com.data.itsv.controller;

import com.data.itsv.aspect.BusinessType;
import com.data.itsv.aspect.Log;
import com.data.itsv.aspect.OperatorType;
import com.data.itsv.log.LogExeManager;
import com.data.itsv.log.LogTaskFactory;
import com.data.itsv.model.AuthUser;
import com.data.itsv.model.vo.Message;
import com.data.itsv.service.AccountService;
import com.data.itsv.service.UserService;
import com.data.itsv.util.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *   post新增,get读取,put完整更新,patch部分更新,delete删除
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
@Api(value = "用户登陆注册接口")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private static final String STR_USERNAME = "userName";
    private static final String STR_CODE = "code";
    private static final String STR_LEVEL = "userLevel";
    private static final String STR_REALNAME = "realName";
    private static final String STR_AVATAR = "avatar";
    private static final String STR_PHONE = "phone";
    private static final String STR_EMAIL = "email";
    private static final String STR_SEX = "sex";
    private static final String STR_WHERE = "createWhere";



    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private LogTaskFactory logTaskFactory;

    @Value("${bootshiro.enableEncryptPassword}")
    private boolean isEncryptPassword;

    @Value("${jwt.timeOut}")
    private String jwtTimeOut;

    /**
     * description 登录签发 JWT ,这里已经在 passwordFilter 进行了登录认证
     *
     * @param request 1
     * @param response 2
     * @return com.usthe.bootshiro.domain.vo.Message
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    @CrossOrigin
    @Log(title = "用户登录")
    public Message accountLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String userName = params.get(STR_USERNAME);
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRole(userName);
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        //long refreshPeriodTime = 1000L;
        String uuidStr = UUID.randomUUID().toString();
        String jwt = JsonWebTokenUtil.issueJWT(uuidStr, userName,
                "token-server", Long.parseLong(jwtTimeOut) , roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-Login-" + userName,uuidStr, Long.parseLong(jwtTimeOut), TimeUnit.SECONDS);
        redisTemplate.delete("JWT-continuity-" + userName);

        AuthUser authUser = userService.getUserByAppId(userName);
        authUser.setPassword(null);
        authUser.setSalt(null);

        return new Message().ok("success","").addData("jwt", jwt).addData("user", authUser);
    }

    /**
     * description 用户账号的注册
     *
     * @param request 1
     * @param response 2
     * @return com.usthe.bootshiro.domain.vo.Message
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    @CrossOrigin

    public Message accountRegister(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        AuthUser authUser = new AuthUser();
        String username = params.get("userName");
        String password = params.get("password");
        String userKey = params.get("userKey");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // 必须信息缺一不可,返回注册账号信息缺失
            return new Message().error( "账户信息缺失");
        }
        if (accountService.isAccountExistByUid(username)) {
            // 账户已存在
            return new Message().error( "账户已存在");
        }

        authUser.setUsername(username);

        if (isEncryptPassword) {
            // 从Redis取出密码传输加密解密秘钥
            String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey);
            password = AesUtil.aesDecode(password, tokenKey);
        }
        String salt = CommonUtil.getRandomString(6);
        // 存储到数据库的密码为 MD5(原密码+盐值)
        authUser.setPassword(Md5Util.md5(password + salt));
        authUser.setSalt(salt);
        authUser.setCreateTime(new Date());
        if (!StringUtils.isEmpty(params.get(STR_CODE))) {
            authUser.setCode(params.get(STR_CODE));
        }
        if (!StringUtils.isEmpty(params.get(STR_LEVEL))) {
            authUser.setUserLevel(params.get(STR_LEVEL));
        }
        if (!StringUtils.isEmpty(params.get(STR_REALNAME))) {
            authUser.setRealName(params.get(STR_REALNAME));
        }
        if (!StringUtils.isEmpty(params.get(STR_AVATAR))) {
            authUser.setAvatar(params.get(STR_AVATAR));
        }
        if (!StringUtils.isEmpty(params.get(STR_PHONE))) {
            authUser.setPhone(params.get(STR_PHONE));
        }
        if (!StringUtils.isEmpty(params.get(STR_EMAIL))) {
            authUser.setEmail(params.get(STR_EMAIL));
        }
        if (!StringUtils.isEmpty(params.get(STR_SEX))) {
            authUser.setSex(Byte.valueOf(params.get(STR_SEX)));
        }
        if (!StringUtils.isEmpty(params.get(STR_WHERE))) {
            authUser.setCreateWhere(Byte.valueOf(params.get(STR_WHERE)));
        }
        authUser.setStatus((byte) 1);

        if (accountService.registerAccount(authUser)) {
            //插入日志
            return new Message().ok("注册成功","");
        } else {
            //插入日志
            return new Message().error("注册失败");
        }
    }

}

package com.data.itsv.service.impl;

import com.data.itsv.mapper.AuthUserMapper;
import com.data.itsv.mapper.AuthUserRoleMapper;
import com.data.itsv.mapper.SUserMapper;
import com.data.itsv.mapper.VServerMapper;
import com.data.itsv.model.*;
import com.data.itsv.service.ProtocolRequest;
import com.data.itsv.service.UserService;
import com.data.itsv.util.DateFormatHelper;
import com.data.itsv.util.HashMapHelper;
import com.data.itsv.util.SocketHelper;
import io.netty.channel.ChannelHandlerContext;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author tomsun28
 * @date 21:15 2018/3/17
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserMapper userMapper;
    @Autowired
    private SUserMapper sUserMapper;
    @Autowired
    private VServerMapper vServerMapper;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;

    @Autowired
    private ProtocolRequest protocolRequest;


    @Value("${cms.ip}")
    private String cmsIp;
    @Value("${cms.port}")
    private String cmsPort;

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }

    @Override
    public List<AuthUser> getUserList() throws DataAccessException {
        return userMapper.selectUserList();
    }

    @Override
    public List<AuthUser> getUserListByRoleId(Integer roleId) throws DataAccessException {
        return userMapper.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean authorityUserRole(String id, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(Integer.parseInt(id));
        authUserRole.setCreateTime(new Date());
        authUserRole.setUpdateTime(new Date());
        return authUserRoleMapper.insert(authUserRole) == 1? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthorityUserRole(String id, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(Integer.parseInt(id));
        authUserRole.setRoleId(roleId);
        return authUserRoleMapper.deleteByUniqueKey(authUserRole) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public AuthUser getUserByAppId(String userName) throws DataAccessException {

        return userMapper.selectByUniqueKey(userName);
    }

    @Override
    public List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId) throws DataAccessException {

        return userMapper.selectUserListExtendByRoleId(roleId);
    }

    @Override
    public SUser cuUserLogin(String account, String password) {
        SUser sUser=new SUser();
        sUser.setAccount(account);
        sUser.setPassword(password);
        SUser sUser1 = sUserMapper.selectByAP(sUser);
        VServer server=new VServer();
        server.setType(1);
        server.setParentCode("00000000000000000000");
        List<VServer> select = vServerMapper.select(server);
        sUser1.setCmsIP(select.get(0).getIp());
        sUser1.setCmsPort(select.get(0).getAccPort()+"");
        VServer mompServer=new VServer();
        mompServer.setIp(cmsIp);
        mompServer.setAccPort(Integer.parseInt(cmsPort));
        StringBuilder  func=new StringBuilder();
        if(sUser1.getFuncPrivilege()!=null){
            String[] split = sUser1.getFuncPrivilege().split(";");
            func.append(split[0]);
            for (String s : split) {
                for(int i=0;i<s.length();i++){
                    char c = s.charAt(i);
                    if("1".equals(c)){
                        func.replace(i,i,c+"");
                    }
                }
            }
        }
        sUser1.setFuncPrivilege(func.toString());

        return  sUser1;
    }

    @Override
    public List<SUser> getUsersByAlarmResId(String alarmResId) {
        List<SUser> sUsers= sUserMapper.getUsersByAlarmResId(alarmResId);
        return null;
    }

    @Override
    public void userRegister(ChannelHandlerContext session, String userId) {
        StringBuffer sf = new StringBuffer();

        // 构造返回命令

        sf.append("UserRegister");
        sf.append(";");
        sf.append("0");

        // 发送请求方
        {
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, sf.toString());
            }
        }

        // 保存用户socket连接信息
        HashMapHelper.getUserConnectionMap().put(userId, session);
        // 上报用户状态信息
        this.reportUserState(userId, OnlineStateModel.ONLINE_STATE,
                DateFormatHelper.date2String(new Date(),
                        "yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void reportUserState(String userCode, int state, String time) {
        // 入库,更新用户状态
        this.updateUserState(userCode, state, time);
        // 2推送到客户端
        // 2.1获取协议
        String strProtocol = protocolRequest
                .reportUserState4StrProtocol(userCode, state);
        // 2.2遍历集合发送信息
        Iterator entries = HashMapHelper.getUserConnectionMap().entrySet()
                .iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            ChannelHandlerContext session = (ChannelHandlerContext) entry.getValue();
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, strProtocol);
            }
        }
        strProtocol = null;
    }

    @Override
    public boolean updateUserState(String userCode, int state, String time) {
//暂时没有实现
       return true;
    }

    @Override
    public void userKeepAlive(ChannelHandlerContext session, String userId) {
        StringBuffer sf = new StringBuffer();

        // 构造返回命令

        sf.append("UserKeepAlive");
        sf.append(";");
        sf.append("0");

        // 发送请求方
        {
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, sf.toString());
            }
        }
    }

    @Override
    public void orderAlarmData(ChannelHandlerContext session, String userId) {
        StringBuffer sf = new StringBuffer();

        // 构造返回命令

        sf.append("UserOrderAlarm");
        sf.append(";");
        sf.append("0");

        // 发送请求方
        {
            if (session != null ) {
                SocketHelper.sendUserClientMsg(session, sf.toString());
            }
        }
    }
}

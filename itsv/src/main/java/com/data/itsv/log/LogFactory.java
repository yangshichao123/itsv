package com.data.itsv.log;

import com.data.itsv.model.LogRest;
import com.data.itsv.model.SLog;

import java.util.Date;

/**
 *   日志对象工厂
 * @author tomsun28
 * @date 9:50 2018/4/22
 */
public class LogFactory {

    private LogFactory() {

    }

    /**
     * 创建日志
     * @param username 用户名
     * @param type 类型
     * @param clientaddr  客户端地址
     * @param content  内容
     * @return
     */
    public static SLog createAccountLog(Integer type,String username, String clientaddr, String content) {
        SLog sLog = new SLog();
      /*  sLog.setClientaddr(clientaddr);
        sLog.setType(type);
        sLog.setContent(content);
        sLog.setUsername(username);
        sLog.setCreatetime(new Date());*/
        return sLog;
    }
/*
    public static LogRest createOperationLog(String userId,String logName,String api, String method, Short succeed, String message) {
        AuthOperationLog operationLog = new AuthOperationLog();
        operationLog.setUserId(userId);
        operationLog.setLogName(logName);
        operationLog.setApi(api);
        operationLog.setMethod(method);
        operationLog.setSucceed(succeed);
        operationLog.setMessage(message);
        operationLog.setCreateTime(new Date());
        return operationLog;
    }*/
}

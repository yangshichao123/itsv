package com.data.itsv.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author tomsun28
 * @date 12:34 2018/2/27
 */
@Data
public class PasswordToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String timestamp;
    private String host;

    public PasswordToken(String appId, String password, String timestamp, String host) {
        this.userName = appId;
        this.timestamp = timestamp;
        this.host = host;
        this.password = password;

    }


    @Override
    public Object getPrincipal() {
        return this.userName;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }


}

package com.data.itsv.shiro.matcher;


import com.data.itsv.model.vo.JwtAccount;
import com.data.itsv.util.JsonWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author tomsun28
 * @date 18:01 2018/3/3
 */
@Component
public class JwtMatcher implements CredentialsMatcher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {

        String jwt = (String) authenticationInfo.getCredentials();
        JwtAccount jwtAccount = null;
        try{
            jwtAccount = JsonWebTokenUtil.parseJwt(jwt,JsonWebTokenUtil.SECRET_KEY);
            String uuid = redisTemplate.opsForValue().get("JWT-Login-" + jwtAccount.getAppId());
            String   newUuid = redisTemplate.opsForValue().get("JWT-continuity-" + jwtAccount.getAppId());
            if(!jwtAccount.getTokenId().equals(uuid)&&!jwtAccount.getTokenId().equals(newUuid)){//如果用户所保存得uuid
                throw new AuthenticationException("expiredJwt");
            }
        } catch(SignatureException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            // 令牌错误
            throw new AuthenticationException("errJwt");
        } catch(ExpiredJwtException e){
            // 令牌过期
            throw new AuthenticationException("expiredJwt");
        } catch(Exception e){
            throw new AuthenticationException("errJwt");
        }
        if(null == jwtAccount){
            throw new AuthenticationException("errJwt");
        }

        return true;
    }
}

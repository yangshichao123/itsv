package com.data.itsv.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.data.itsv.lock.SpinLockDemo;
import com.data.itsv.model.vo.JwtAccount;
import com.data.itsv.model.vo.Message;
import com.data.itsv.shiro.token.JwtToken;
import com.data.itsv.util.JsonWebTokenUtil;
import com.data.itsv.util.RequestResponseUtil;
import com.data.itsv.util.ReturnStatusConstant;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 *  支持restful url 的过滤链  JWT json web token 过滤器，无状态验证
 * @author tomsun28
 * @date 0:04 2018/4/20
 */
@Slf4j
@Data
public class BonJwtFilter extends AbstractPathMatchingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonJwtFilter.class);
    private static final String STR_EXPIRED = "expiredJwt";

    private String intervalTime;
    private String jwtTimeOut;

    private StringRedisTemplate redisTemplate;
   // private AccountService accountService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = getSubject(servletRequest,servletResponse);


      /*  //记录调用api日志到数据库
        LogExeManager.getInstance().executeLogTask(LogTaskFactory.bussinssLog(WebUtils.toHttp(servletRequest).getHeader("appId"),
                WebUtils.toHttp(servletRequest).getRequestURI(), WebUtils.toHttp(servletRequest).getMethod(),(short)1,null));
*/
        boolean isJwtPost = (null != subject && !subject.isAuthenticated()) && isJwtSubmission(servletRequest);
        // 判断是否为JWT认证请求
        if (isJwtPost) {
            AuthenticationToken token = createJwtToken(servletRequest);
            try {
                subject.login(token);

                JwtToken jwtToken = (JwtToken)token;
                JwtAccount jwtAccount = JsonWebTokenUtil.parseJwt(jwtToken.getJwt(),JsonWebTokenUtil.SECRET_KEY);
                HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);;
                //判断是否在规则内   即离过期时间达到
                long time = jwtAccount.getIssuedAt().getTime()+Long.parseLong(intervalTime)*1000;
                long timeEnd= new Date().getTime();
                if(time<timeEnd){
                    try {
                        SpinLockDemo.myLock();
                        String logToke = redisTemplate.opsForValue().get("JWT-Login-" + jwtAccount.getAppId());
                        String logTokeNew = redisTemplate.opsForValue().get("JWT-continuity-" + jwtAccount.getAppId());

                        if(logTokeNew==null||logToke==null){
                            String uuidStr = UUID.randomUUID().toString();
                            String jwt = JsonWebTokenUtil.issueJWT(uuidStr,jwtAccount.getAppId(),
                                    "token-server", Long.parseLong(jwtTimeOut) , jwtAccount.getRoles(), null, SignatureAlgorithm.HS512);
                            httpResponse.setHeader("Access-Control-Expose-Headers","x-auth-token");
                            httpResponse.setHeader("x-auth-token", jwt);
                            if(logToke!=null) {
                                redisTemplate.opsForValue().set("JWT-Login-" + jwtAccount.getAppId(), logToke, 30, TimeUnit.SECONDS);
                            }else{
                                redisTemplate.opsForValue().set("JWT-Login-" + jwtAccount.getAppId(), redisTemplate.opsForValue().get("JWT-continuity-" + jwtAccount.getAppId()), 30, TimeUnit.SECONDS);

                            }
                            redisTemplate.opsForValue().set("JWT-continuity-" + jwtAccount.getAppId(),uuidStr,  Long.parseLong(jwtTimeOut), TimeUnit.SECONDS);
                        }
                    }catch (Exception e){
                        log.error(e.getMessage(),e);
                    }finally {
                        SpinLockDemo.myUnLock();
                    }

                }

                return this.checkRoles(subject,mappedValue);
            }catch (AuthenticationException e) {

                // 如果是JWT过期
                if (STR_EXPIRED.equals(e.getMessage())) {
                    // 这里初始方案先抛出令牌过期，之后设计为在Redis中查询当前appId对应令牌，其设置的过期时间是JWT的两倍，此作为JWT的refresh时间
                    // 当JWT的有效时间过期后，查询其refresh时间，refresh时间有效即重新派发新的JWT给客户端，
                    // refresh也过期则告知客户端JWT时间过期重新认证

                    // 当存储在redis的JWT没有过期，即refresh time 没有过期
                   /* String appId = WebUtils.toHttp(servletRequest).getHeader("appId");
                    String jwt = WebUtils.toHttp(servletRequest).getHeader("authorization");
                    String refreshJwt = redisTemplate.opsForValue().get("JWT-SESSION-"+appId);
                    if (null != refreshJwt && refreshJwt.equals(jwt)) {
                        // 重新申请新的JWT
                        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
                        String roles = accountService.loadAccountRole(appId);
                        //seconds为单位,10 hours
                        long refreshPeriodTime = 36000L;
                        String newJwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(),appId,
                                "token-server",refreshPeriodTime >> 1,roles,null, SignatureAlgorithm.HS512);
                        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
                        redisTemplate.opsForValue().set("JWT-SESSION-"+appId,newJwt,refreshPeriodTime, TimeUnit.SECONDS);
                        Message message = new Message().ok(1005,"new jwt").addData("jwt",newJwt);
                        RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
                        return false;
                    }else {
                        // jwt时间失效过期,jwt refresh time失效 返回jwt过期客户端重新登录
                        Message message = new Message().error(1006,"expired jwt");
                        RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
                        return false;
                    }*/
                    // jwt时间失效过期,jwt refresh time失效 返回jwt过期客户端重新登录
                    Message message = new Message().error("jwtToken过期 请重新登陆", ReturnStatusConstant.TOKEN_OVERTIME);
                    RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
                    return false;
                }
                // 其他的判断为JWT错误无效
                Message message = new Message().error("无效dJwtToken 请重新登陆",ReturnStatusConstant.TOKEN_INVALID);
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
                return false;

            }catch (Exception e) {
                // 其他错误
                //LOGGER.error(IpUtil.getIpFromRequest(WebUtils.toHttp(servletRequest))+"--JWT认证失败"+e.getMessage(),e);
                // 告知客户端JWT错误1005,需重新登录申请jwt
                Message message = new Message().error("无效dJwtToken 请重新登陆",ReturnStatusConstant.TOKEN_INVALID);
                RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
                return false;
            }
        }else {
            // 请求未携带jwt 判断为无效请求
            Message message = new Message().error("未携带token 无效请求 请重新登陆",ReturnStatusConstant.TOKEN_EMPTY);
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest,servletResponse);

        // 未认证的情况上面已经处理  这里处理未授权
        if (subject != null && subject.isAuthenticated()){
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
            Message message = new Message().error("没有权限",ReturnStatusConstant.TOKEN_NO_PERMISSION);
            RequestResponseUtil.responseWrite(JSON.toJSONString(message),servletResponse);
        }
        // 过滤链终止
        return false;
    }

    private boolean isJwtSubmission(ServletRequest request) {

        String jwt = RequestResponseUtil.getHeader(request,"authorization");
        String appId = RequestResponseUtil.getHeader(request,"appId");
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt)
                && !StringUtils.isEmpty(appId);
    }

    private AuthenticationToken createJwtToken(ServletRequest request) {

        Map<String,String> maps = RequestResponseUtil.getRequestHeaders(request);
        String appId = maps.get("appId");
        String ipHost = request.getRemoteAddr();
        String jwt = maps.get("authorization");
        String deviceInfo = maps.get("deviceInfo");

        return new JwtToken(ipHost,deviceInfo,jwt,appId);
    }

    /**
     * description 验证当前用户是否属于mappedValue任意一个角色
     *
     * @param subject 1
     * @param mappedValue 2
     * @return boolean
     */
    private boolean checkRoles(Subject subject, Object mappedValue){
        String[] rolesArray = (String[]) mappedValue;
        return rolesArray == null || rolesArray.length == 0 || Stream.of(rolesArray).anyMatch(role -> subject.hasRole(role.trim()));
    }


    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /*public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }*/
   /* @Override
    protected void cleanup(ServletRequest request, ServletResponse response, Exception existing) throws ServletException, IOException {

        JwtToken jwtToken = (JwtToken)createJwtToken(request);
        JwtAccount jwtAccount = JsonWebTokenUtil.parseJwt(jwtToken.getJwt(),JsonWebTokenUtil.SECRET_KEY);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);;
        //判断是否在规则内   即离过期时间达到
        long time = jwtAccount.getIssuedAt().getTime();
       long timeEnd= new Date().getTime()+Long.parseLong(intervalTime)*1000;
        if(time<timeEnd){
            String jwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(),jwtAccount.getAppId(),
                    "token-server", Long.parseLong(jwtTimeOut) , jwtAccount.getRoles(), null, SignatureAlgorithm.HS512);
            httpResponse.setHeader("Access-Control-Expose-Headers","200");
            httpResponse.setHeader("x-auth-token", jwt);
        }

        Exception exception = existing;

        try {
            this.afterCompletion(request, response, exception);
            if (log.isTraceEnabled()) {
                log.trace("Successfully invoked afterCompletion method.");
            }
        } catch (Exception var6) {
            if (existing == null) {
                exception = var6;
            } else {
                log.debug("afterCompletion implementation threw an exception.  This will be ignored to allow the original source exception to be propagated.", var6);
            }
        }

        if (exception != null) {
            if (exception instanceof ServletException) {
                throw (ServletException)exception;
            } else if (exception instanceof IOException) {
                throw (IOException)exception;
            } else {
                if (log.isDebugEnabled()) {
                    String msg = "Filter execution resulted in an unexpected Exception (not IOException or ServletException as the Filter API recommends).  Wrapping in ServletException and propagating.";
                    log.debug(msg);
                }

                throw new ServletException(exception);
            }
        }
    }*/
}

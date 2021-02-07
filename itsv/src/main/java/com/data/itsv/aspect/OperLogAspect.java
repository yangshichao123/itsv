package com.data.itsv.aspect;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.data.itsv.log.LogExeManager;
import com.data.itsv.log.LogTaskFactory;
import com.data.itsv.model.SLog;
import com.data.itsv.service.SLogService;
import com.data.itsv.util.RequestResponseUtil;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @program: open-his
 * @description: 操作日志记录处理
 * @author: Daigl
 * @create: 2021-02-04 21:42
 **/
@Component
@Aspect
@Log4j2
@Slf4j
public class OperLogAspect {
    @Autowired
    private LogTaskFactory logTaskFactory;

    /**
     * 声明切面
     * 只要Controller的方法中有@log注解就切入 重要
     */
    @Pointcut("@annotation(com.data.itsv.aspect.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }



            // *========数据库日志=========*//
            SLog operLog = new SLog();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // ip地址
            String ipAddr = getRemoteHost(request);
            // 请求路径
            String requestUrl = request.getRequestURL().toString();


            operLog.setIp(ipAddr);
            // 获取当前的用户id
            String appId = RequestResponseUtil.getHeader(request,"appId");

            if (appId != null) {
                operLog.setUserId(appId);
            }

            if (e != null) {
                operLog.setError(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog,request);
            //设置操作时间
            operLog.setCreatetime(DateUtil.date());
            // 保存数据库 *******************************************重要。
            //保存您的日志数据到数据库
            LogExeManager.getInstance().executeLogTask( logTaskFactory.crezteLog(operLog));
            log.info("用户id为：" +operLog.getUserId()+" 调用："+methodName+" "+operLog.getContent()+" retrun:"+JSON.toJSONString(jsonResult));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SLog operLog,HttpServletRequest request) throws Exception {
        // 设置action动作
        operLog.setType(log.logType());
        // 设置标题
        operLog.setContent(log.title());
        // 设置操作人类别
        operLog.setOptType(log.operatorType());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog,request);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SLog operLog,HttpServletRequest request) throws Exception {
        String requestMethod = request.getMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setParameter(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?,?> paramsMap = (Map<?,?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setParameter(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }


    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest
                || o instanceof HttpServletResponse;
    }

    /**
     * 99      * 获取目标主机的ip
     * 100      * @param request
     * 101      * @return
     * 102
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.contains("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


}


package top.wayne06.generalbackend.aop;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * request-response log interceptor
 *
 * @author wayne
 */
@Aspect
@Component
@Slf4j
public class LogInterceptor {

    /**
     * execute intercepting
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* top.wayne06.generalbackend.controller.*.*(..))")
    public Object doInterceptor(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // get request URI(Uniform Resource Identifier)
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        String url = httpServletRequest.getRequestURI();
        // generate unique id of request
        String requestId = UUID.randomUUID().toString();
        // get request arguments
        Object[] args = point.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";
        // record request log
        log.info("request start，id: {}, path: {}, ip: {}, params: {}",
                requestId, url, httpServletRequest.getRemoteHost(), reqParam);
        // execute the original method
        Object result = point.proceed();
        // record the response log
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);
        return result;
    }
}


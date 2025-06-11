package org.jit.sose.web.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j // 写入日志方式一
@Aspect // 表示这个类作为一个切面。若是springmvc，则需要在配置文件中配置
@Component // 表示这个类要放在IOC容器中
public class WebLogAspect {

    // 写入日志方式二
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    // 设置日志文件范围
    @Pointcut("execution(public * org.jit.sose.controller.*.*(..))")
    public void webLog() {
        logger.trace("**************日志开启*************");
    }

    /**
     * 使用AOP前置通知拦截请求参数信息
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        logger.info("***************前置通知**************");
        logger.info("类名：" + joinPoint.getTarget().getClass().getName());
        logger.info("方法名：" + joinPoint.getSignature().getName());
        logger.info("参数：");
        for (Object object : joinPoint.getArgs()) {
            logger.info("	" + object);
        }
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("请求路径：{}", request.getRequestURL().toString());
        logger.info("请求方式：{}", request.getMethod());
        logger.info("请求IP：{}", request.getRemoteAddr());
        log.info("请求头：{}", request.getHeader("sessionKey"));
        log.info("请求token：{}", request.getHeader("Authorization"));
        Enumeration<String> enu = request.getParameterNames();
        if (enu.hasMoreElements()) {
            logger.info("请求路径参数: ");
        }
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("	name:{},value:{}", name, request.getParameter(name));
        }
    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("***************返回通知**************");
        logger.info("请求返回内容: " + ret);
    }

    /**
     * 后置通知
     *
     * @param ret
     * @throws Throwable
     */
//	@After("webLog()")
//	public void doAfter(JoinPoint joinPoint) throws Throwable {
//		logger.info("后置通知");
//	}

    /**
     * 环绕通知
     *
     */
//	@Around("webLog()")
//	public Object doAround(ProceedingJoinPoint pJoinPoint) throws Throwable {
//		System.out.println("====前====");
//		// 执行目标方法
//		Object object = pJoinPoint.proceed();
//		System.out.println("环绕通知result：" + object);
//		System.out.println("====后====");
//		return object;
//	}

    /**
     * 异常通知
     *
     * @param ret
     * @throws Throwable
     */
//	@AfterThrowing(value = "webLog()", throwing = "ex")
//	public void doAfterThrowing(JoinPoint joinPoint, Exception ex) throws Throwable {
//		System.out.println("异常通知：" + ex);
//	}

}

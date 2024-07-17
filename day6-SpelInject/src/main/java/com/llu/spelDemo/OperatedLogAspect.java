package com.llu.spelDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
@Aspect
@Component
/**
 * des : 拦截所有的Controller方法，并且记录日志
 */
public class OperatedLogAspect {
    /**
     * 该函数是一个切面点注解方法，
     * 用于匹配被com.llu.CkOperatedLog注解的方法以及在com包下controller目录中所有方法的执行。
     */
    @Pointcut("@annotation(com.llu.spelDemo.CkOperatedLog)" )
    public void annotation() {
    }

    /**
     * 这是一个使用Spring AOP切面编程的函数，
     * 通过@Around注解定义了一个环绕通知，
     * 其参数为一个ProceedingJoinPoint对象，表示当前执行的方法。
     * 函数的功能是在目标方法执行前添加日志，
     * 然后通过调用proceed()方法执行目标方法并返回结果。
     * @param pjd
     * @return
     * @throws Throwable
     */
    @Around("annotation()")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
        addLog(pjd);
        return pjd.proceed();
    }

    /**
     * 添加日志的切面逻辑。
     * 在方法执行前后，通过织入切面逻辑来实现日志的记录。此方法专门处理日志的添加，
     * 包括操作日志的详细信息和请求URL的记录。
     *
     * @param pjd 切面 ProceedingJoinPoint 对象，用于获取方法签名和参数信息。
     * @throws Throwable 如果执行过程中出现异常，将被捕获并处理。
     */
    private void addLog(ProceedingJoinPoint pjd) throws Throwable {
        try {
            // 获取方法签名，以便获取方法名和参数名。
            MethodSignature methodSignature = (MethodSignature) pjd.getSignature();
            // 获取方法参数，用于后续拼接参数字符串。
            Object[] args = pjd.getArgs();
            // 获取参数名，用于SPEL表达式解析。
            String[] parameterNames = methodSignature.getParameterNames();
            // 获取具体的方法对象，以便获取方法上的注解信息。
            Method method = methodSignature.getMethod();
            // 通过方法对象获取自定义注解CkOperatedLog，以获取日志相关属性。
            CkOperatedLog annotation = method.getAnnotation(CkOperatedLog.class);

            // 从注解中获取日志属性值。
            String spelString = annotation.param();
            String module = annotation.module();
            String operation = annotation.operation();
            String description = annotation.description();
            // 使用SPEL表达式解析参数，生成参数字符串。
            // 参数经过spel表达式转换后值
            String paramStr = SpelUtil.parseSpEL(spelString, args, parameterNames);

            // 打印日志信息，包括模块、操作、描述和参数。
            System.out.println("模块:" + module + ",操作:" + operation + ",描述:" + description + ",参数:" + paramStr);

            // 获取当前请求的URL，用于记录请求地址。
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 打印请求URL。
            System.out.println("请求地址:" + request.getRequestURL().toString());

            // TODO: 获取用户登录信息，并进行后续的日志记录操作，如发送到MQ进行存储。
            // 再获取用户登录信息

            // TODO: 发送给MQ,然后进行落库
        } catch (Exception e) {
            // 捕获并处理异常，防止日志记录影响主业务逻辑。
            e.printStackTrace();
            System.out.println("捕获异常，防止影响业务方法");
        }
    }

}

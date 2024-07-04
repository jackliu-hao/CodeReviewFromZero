package com.lluu.Demo1;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author jackliu  Email:
 * @description: 通过反射命令执行
 * @Version
 * @create 2024-07-04 20:55
 */
public class DoubleReflectRunExec {
    public static void main(String[] args) throws Exception {
        // 正常
//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("calc");
        // 通过反射
        Class<Runtime> runtimeClass = Runtime.class;
        Method getRuntimeMethod = runtimeClass.getDeclaredMethod("getRuntime");
//        Runtime runtime = (Runtime) getRuntimeMethod.invoke(null, null);
//        runtime.exec("calc");
        Class<? extends Method> methodClass = getRuntimeMethod.getClass();
//        Method methodInvoke = methodClass.getDeclaredMethod("invoke",null,null);

        Method methodInvoke = methodClass.getDeclaredMethod("invoke", Object.class, Object[].class);

        Runtime runtime = (Runtime) methodInvoke.invoke(getRuntimeMethod, new Object[]{null, null});
        runtime.exec("calc");

    }
}

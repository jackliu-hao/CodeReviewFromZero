package com.lluu;

import com.lluu.bean.Person;

/**
 * @author jackliu  Email:
 * @description: 反射测试
 * @Version
 * @create 2024-07-04 20:37
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {


        //通过classForname获取Class
//        Class<?> aClass = Class.forName("com.lluu.bean.Person");
         // 实例化对象
//        aClass.newInstance();

        // 通过 类加载器获取 Class
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        Class<?> aClass1 = classLoader.loadClass("com.lluu.bean.Person");
//        aClass1.newInstance();


    }
}

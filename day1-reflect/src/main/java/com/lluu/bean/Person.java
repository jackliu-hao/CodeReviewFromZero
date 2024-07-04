package com.lluu.bean;

import lombok.Data;

/**
 * @author jackliu  Email:
 * @description:
 * @Version
 * @create 2024-07-04 20:37
 */
@Data
public class Person {
    static {
        System.out.println("我是静态代码块的内容");
    }
    public static void staticMethod(){
        System.out.println("我是静态方法");
    }

    public Person() {
        System.out.println("我是空参构造器");
    }

    private String name;
    private Integer age;


}

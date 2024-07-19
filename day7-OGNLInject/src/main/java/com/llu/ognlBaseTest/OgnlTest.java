package com.llu.ognlBaseTest;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlTest {

    /**
     * OgnlContext用法
     * 1.使用Ognl表达式语言取值，如果取非根元素的值，必须用#号
     * 2.使用Ognl表达式语言取值，如果取根元素的值，不用#号
     * 3.Ognl可以调用静态方法
     */

    public static void test1() throws OgnlException {
        //创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();
        /**
         * 1.OgnlContext放入基本变量数据
         */
        //放入数据
        context.put("cn","China");
        //获取数据（map）
        String value = (String)context.get("cn");

        System.out.println(value);

        /**
         * 2.OgnlContext放入对象数据
         */
        //创建对象，设置对象属性
        User user = new User();
        user.setId(100);
        user.setName("Jack");
        //【往非根元素放入数据，取值的时候表达式要用“#”】
        context.put("user",user);
        //获取对象属性
        //使用这种方式也可以获取
        Object s = context.get("user");
        System.out.println(s);

        //使用Ognl表达式来获取
        //举例：例如标签<s:a value="#user.id">取值，实际上就是运行了下面的代码获取的
        //先构建一个Ognl表达式，再解析表达式
        Object ognl = Ognl.parseExpression("#user.id");//构建Ognl表达式
        Object value1 = Ognl.getValue(ognl, context, context.getRoot());//解析表达式
        System.out.println(value1);
    }

    public static void test2() throws OgnlException {
        OgnlContext context = new OgnlContext();
        User user = new User();
        user.setId(100);
        user.setName("Jack");

        context.put("user",user);
        Object ognl = Ognl.parseExpression("#user.id");//构建Ognl表达式
        System.out.println(context.getRoot());
        Object value1 = Ognl.getValue(ognl, context, context.getRoot());//解析表达式
        System.out.println(value1);
    }

    /**
     * 获取根元素信息
     * @throws OgnlException
     */
    public static void test3() throws OgnlException {
        OgnlContext context = new OgnlContext();
        User user = new User();
        user.setId(100);
        user.setName("Jack");

        context.setRoot(user);

        Object ognl1 = Ognl.parseExpression("id");//构建Ognl表达式
        Object value2 = Ognl.getValue(ognl1, context, context.getRoot());//解析表达式
        System.out.println(value2);
    }

    /**
     *  //ognl对静态方法调用的支持
     * @throws OgnlException
     */
    public static void test4() throws OgnlException {

        //创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();

        //Ognl表达式语言，调用类的静态方法
//        Object ognl = Ognl.parseExpression("@Math@floor(10.9)");
        //由于Math类在开发中比较常用，所有也可以这样写
//        Object ognl = Ognl.parseExpression("@@floor(10.9)");
        Object ognl = Ognl.parseExpression("@java.lang.Runtime@getRuntime().exec('calc')");
        String expression = "@java.lang.Runtime@getRuntime().exec('calc')";
        Object value = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(value);

    }

    public static void main(String[] args) throws OgnlException {
        test4();
    }


}

